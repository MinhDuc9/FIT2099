package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Abstract class representing healing items in the game.
 * <p>
 * This class serves as a base for different types of healing items that can restore health points to actors.
 * Each healing item must have a specific amount of health points it can restore, which is defined in each subclass.
 * Healing items can be characterized by their name, display character on the map, and whether they are portable.
 */
public abstract class HealingItem extends Item {
    /**
     * The amount of health points that the item can heal.
     */
    protected int healingPoint;

    /**
     * Constructor for the HealingItem class.
     * <p>
     * Initializes a new healing item with a name, display character, and portability status.
     * The specific amount of healing points must be defined in the subclasses.
     *
     * @param name The name of the healing item.
     * @param displayChar The character that will represent the item on the game map.
     * @param portable True if the item can be picked up and carried; false if it cannot be moved.
     */
    public HealingItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Abstract method to return the amount of healing points this item provides.
     * <p>
     * Subclasses must implement this method to return the specific amount of health points the item can restore
     * when used by an actor. This allows different healing items to have different effects.
     *
     * @return the amount of healing points the item restores.
     */
    public abstract int getHealingPoints();
}
