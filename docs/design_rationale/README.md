# Design Rationale for REQ1: The Intern of the Static Factory

## Item Interaction

The focus on item pickup and drop-off as the sole interactions in REQ1 is intentional to introduce players to the basic mechanics without overwhelming them with more complex interactions that are reserved for later requirements (REQ4). This step-by-step approach in gameplay complexity allows players to familiarize themselves with the game's controls and objectives gradually.

## Item Properties

Both items (large bolts and metal sheets) are designed to be portable, reflecting their role as collectible objects essential for factory production. Using distinct symbols ('+' for bolts and '%' for sheets) visually differentiates these items on the map, aiding in quick player recognition and interaction planning.

## Implementation Specifics

The implementation of `LargeBolt` and `MetalSheet` classes as extensions of the `Item` class ensures they integrate seamlessly with the existing game engine's item handling features. This approach leverages the engine's capabilities for item management, such as pickup and inventory system, and ensures consistency across different types of game objects.

## Testing

The prescribed testing method—manually placing items on the map, collecting them, and returning them to the spaceship—covers the primary game loop intended for this requirement. This testing ensures that the basic functionalities of moving, picking up, and dropping items work as expected. Additionally, the ability to stack items on the same floor tile is tested, which is crucial for managing multiple items within the game's spatial constraints.

## Justification of Design Choices

1. **Simplification of Implementation:** Extending the `LargeBolt` and `MetalSheet` classes from the `Item` class leverages the existing functionalities provided by the game engine, such as inventory management, pickup, and drop-off capabilities. This approach reduces the complexity involved in coding new behaviors from scratch and ensures that these items seamlessly integrate into the game’s broader object management system.

2. **Consistency Across Game Elements:** By inheriting from the `Item` class, both `LargeBolt` and `MetalSheet` adhere to a consistent interface for all interactive game elements. This consistency is crucial for maintaining a stable and predictable game environment where different types of items behave in expected ways, thus improving the user experience and reducing the potential for bugs.

3. **Reusability of Code:** Utilizing inheritance allows for the reuse of common item behavior such as how items are displayed on the game map (`displayChar`), whether they can be carried (`portable`), and how they are identified within the game (`name`). This reusability not only speeds up the development process but also ensures that enhancements or bug fixes in the parent class propagate to derived classes, increasing maintainability.


# Design Rationale for REQ2: The Moon's Flora

## Overview

The design of the "Inheritree" lifecycle in the game from sapling to mature tree and its ability to drop fruits is engineered to comply with key software development principles taught in our unit, notably the DRY and SOLID principles. The rationale behind these design choices is grounded in ensuring code efficiency, maintainability, and scalability.

## Application of DRY Principle

The implementation of the `Inheritree` class as an abstract base class for both `SaplingTree` and `MatureTree` ensures that common functionalities, such as the inability of actors to enter the tree spaces, are centralized. This prevents code duplication across tree types regarding their shared characteristics as non-traversable ground types.

### Advantages:
- **Reduces Redundancy:** Centralizing common logic in `Inheritree` reduces repeated code across different tree stages, minimizing errors and easing maintenance.
- **Simplifies Updates:** Changes to common tree behavior need to be made in just one place, simplifying updates and fixes.

### Disadvantages:
- **Less Flexibility in Unique Behaviors:** Any exception in common behavior between tree stages might require overriding methods, which could lead to errors if not handled carefully.

## Application of SOLID Principles

### Single Responsibility Principle (SRP)
Each class has a single responsibility. `Inheritree` manages the characteristics common to all tree stages, `SaplingTree` manages growth and small fruit dropping, and `MatureTree` manages large fruit dropping.

### Open/Closed Principle (OCP)
The design is open for extension but closed for modification. New types of trees or stages can be added without modifying the existing code. For example, adding a new tree stage would involve creating a new class that extends `Inheritree`.

### Liskov Substitution Principle (LSP)
Subtypes (`SaplingTree` and `MatureTree`) can be substituted for their base type (`Inheritree`) without affecting the functioning of the program, ensuring that the tree behaves as expected as it progresses through different stages.

### Interface Segregation Principle (ISP)
Not directly applicable as there is no use of interfaces in this specific implementation, but the principle guides future designs to avoid forcing classes to implement interfaces they do not use.

### Dependency Inversion Principle (DIP)
This principle is partially applied through the use of abstract classes to generalize tree behavior rather than hard-coding dependencies on specific tree types.

### Advantages:
- **Scalability and Maintainability:** Following SOLID principles enhances the scalability of the game architecture, allowing easy addition of new tree types or fruit-related interactions.
- **Robustness:** Adhering to LSP and OCP ensures that the system remains robust and less prone to bugs during extensions or modifications.

### Disadvantages:
- **Complexity:** The architectural design could introduce additional complexity, which may require more time to understand and manage, particularly for new developers.

## Extensibility

The current design allows easy addition of new types of trees or changes in how trees grow and produce fruits. For instance, if a new character that interacts differently with trees is added in the future, new methods can be implemented in the `Inheritree` class or its subclasses without altering the existing tree behavior.

## Conclusion

The design choices made for the "Inheritree" and its lifecycle in REQ2 strategically adhere to established software development principles to optimize maintainability, extendibility, and robustness. While this introduces some complexity, it significantly enhances the game's capacity to evolve and incorporate new features seamlessly.

# Design Rationale for REQ3: The Moon’s (Hostile) Fauna

## Overview of Hostile Entity Mechanics

In REQ3, the introduction of hostile Huntsman spiders and their behavior is designed to provide a survival challenge to the Intern. The spawning of these creatures from craters with a set probability represents an unpredictable element that keeps the gameplay engaging and dynamic.

## Implementation of Spawning Mechanism

The design utilizes a `Spawner` class that embodies the principles of the Single Responsibility Principle (SRP) by separating the spawning logic from the ground objects (`Crater`) themselves. The `Spawn` interface ensures that any game object that can spawn entities, such as the Crater, adheres to a standard method of spawning, promoting a consistent implementation strategy across different spawning objects.

### Advantages:
- **Flexibility and Extensibility:** The design is flexible, allowing for the addition of different types of spawnable entities or spawning mechanisms without altering existing classes.
- **Decoupling:** The `Spawner` class is decoupled from specific ground types, which enhances modularity and makes the system easier to modify or extend.

### Disadvantages:
- **Complexity:** The additional abstraction layer can introduce complexity, making the system harder to understand and potentially increasing the learning curve for new developers or maintainers.

## Design of Hostile Creature Behavior

The `HuntsmanSpider`’s behavior is governed by a set of `Behaviour` objects, which are prioritized and managed through a map structure. This design adheres to the Open/Closed Principle (OCP) by being open to extension (new behaviors can be added or modified independently) and closed for modification (changing the spider's basic handling does not require changes to the class itself).

### Advantages:
- **Scalability:** New behaviors can be easily added to the Huntsman Spider or other actors without modifying the existing codebase significantly.
- **Maintainability:** Behaviors are encapsulated in separate classes, reducing the complexity within the actor classes and enhancing maintainability.

### Disadvantages:
- **Potential for Over-engineering:** For simple behavior requirements, this approach might be more complex than necessary, potentially leading to over-engineering :))) could be.

## Interaction Design

The choice to limit the spider's attack capability to the Intern and not other hostile entities focuses the challenge on player survival. This design decision simplifies the interaction model and ensures that gameplay remains focused and understandable.

## Future Extensions and OCP

The current design supports the Open/Closed Principle effectively:
- **Adding New Creatures:** Introducing new hostile creatures can be achieved by implementing the `Actor` class and assigning appropriate behaviors without altering existing classes.
- **Introducing New Behaviors:** New behaviors, such as different attack patterns or movement strategies, can be added as classes that implement the `Behaviour` interface.

## Testing and Validation

The testing in `Application` ensure that the spawning and behavior mechanics are validated under real gameplay conditions, checking both the functionality and the balance of the creature interactions.

## Conclusion

The design for REQ3 leverages DRY and SOLID principles to create a robust, maintainable, and extensible system for handling hostile entities within the game. While this approach adds some complexity, it significantly benefits the game's capacity to evolve and incorporate sophisticated gameplay mechanics seamlessly.


# Design Rationale for REQ4: Special Scraps

## Overview of Interaction Mechanics with Special Scraps

In REQ4, the introduction of interactive special scraps like the metal pipe extends the Intern's capabilities beyond simple item collection to include defensive and survival mechanisms. This requirement enhances gameplay complexity by integrating combat elements, thereby enriching the player's strategic engagement with the game environment.

## Implementation of Special Scrap Items

Special scraps like the metal pipe are implemented as subclasses of `WeaponItem`, allowing them to be both collectible and usable in combat scenarios. This approach adheres to the Single Responsibility Principle (SRP) by maintaining distinct functionalities for item collection and combat within separate class hierarchies but under a cohesive system that manages interactive objects.

### Advantages:
- **Modularity and Reusability:** By extending from `WeaponItem`, the metal pipe benefits from all weapon-related methods and properties, promoting code reusability and modularity.
- **Extendability for Future Gameplay Elements:** The design easily accommodates additions such as new special scraps with unique effects or enhancements, aligning with the Open/Closed Principle (OCP).

### Disadvantages:
- **Complexity in Interaction Management:** As the number of interactable item types grows, managing different interactions (e.g., combat vs. non-combat uses) might become cumbersome, potentially requiring a more sophisticated management system.

## Integration of Combat Mechanics

The metal pipe and intrinsic weapon (bare hands) are integrated into the player's list of possible actions when encountering hostile creatures. This design choice ensures that combat options are dynamically related to the player's current inventory, adhering to the principle of Least Astonishment by providing intuitive and expected interactions.

### Advantages:
- **Enhanced Player Engagement:** Offering multiple combat options increases tactical depth and player engagement.
- **Flexibility in Gameplay:** Players can choose how to interact with threats based on their current inventory and strategic preferences.

### Disadvantages:
- **Increased Decision Complexity:** With more options available, players might experience decision fatigue or confusion in high-pressure situations.

## Consumption of Healing Items

Healing items (fruits from the "Inheritree") are implemented to provide health recovery, a critical aspect of survival gameplay. This mechanism uses the Strategy Pattern by allowing different healing strategies (small vs. large fruits) to be applied depending on the item consumed.

### Advantages:
- **Strategic Resource Management:** Players must decide when and how to use healing items, adding a layer of strategic resource management.
- **Direct Feedback Mechanism:** The immediate increase in health points provides direct feedback on action effectiveness, satisfying player actions.

### Disadvantages:
- **Inventory Management Complexity:** Managing multiple item types for health recovery can complicate inventory management, especially as more item types are introduced.

## Future Extensions and Design Scalability

The current design supports future gameplay extensions effectively:
- **Adding New Special Scraps:** Introducing new types of special scraps that can be used for different purposes (e.g., shields, traps) can be easily managed by extending the `Item` or `WeaponItem` classes.
- **New Combat Mechanics:** Incorporating new combat mechanics or enemy types requires minimal changes to the existing system, with behaviors and interactions being added as modular components.

## Testing and Validation

The provided testing instructions ensure a thorough evaluation of both new combat mechanics and the effectiveness of healing items, verifying that the game behaves as expected under various scenarios and that player actions lead to appropriate consequences.

## Conclusion

The design for REQ4 leverages principles such as DRY and SOLID to ensure that the game's extension with special scraps and combat mechanics is both maintainable and scalable. While introducing complexities in interaction management and inventory control, the benefits in gameplay depth and strategic engagement justify these complexities. This approach not only enhances the current gameplay but also sets a solid foundation for future expansions.
