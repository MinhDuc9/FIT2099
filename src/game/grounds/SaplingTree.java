package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.items.SmallFruit;

import java.util.List;
import java.util.Random;

/**
 * A ground type representing a sapling tree that can grow into a mature tree and drop small fruits.
 *
 * This class extends Inheritree and simulates a young tree in the game world. It includes functionality
 * to grow into a mature tree over time and to randomly drop small fruits in adjacent accessible locations.
 */
public class SaplingTree extends Inheritree {
    /**
     * Tracks the age of the sapling tree in turns.
     */
    private int age = 0;

    public SaplingTree() {
        super('t');
    }

    /**
     * Performs actions each game tick, such as growing into a mature tree or dropping fruits.
     *
     * This method is called once per turn, allowing the tree to age. When the sapling reaches an age of 5 turns,
     * it transforms into a mature tree. Additionally, there is a chance each tick to drop a small fruit at one
     * of the adjacent locations that do not block thrown objects, provided the tree has not yet matured.
     *
     * @param location The location of this ground on the game map.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++; // Increment the age of the sapling each turn

        Random random = new Random();

        int randomNumber = random.nextInt(100);

        // Transform the sapling into a mature tree at age 5
        if (age == 5) {
            location.setGround(new MatureTree());
        }

        // Get the correct coordinates surround the tree
        List<Location> coordinates = location.getExits().stream()
                .map(Exit::getDestination)
                .filter(v -> !v.getGround().blocksThrownObjects())
                .toList();

        // If the random chance is successful, drop a fruit at a random accessible location
        if (randomNumber < 30 && age < 5) {
            int number = random.nextInt(coordinates.size());
            coordinates.get(number).addItem(new SmallFruit());
        }
    }
}
