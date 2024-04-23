package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.HealingAction;

/**
 * Represents a Large Fruit item that actors can consume for healing in the game.
 *
 * This class extends HealingItem to provide a specific healing item, the Large Fruit, which
 * can be used by actors to restore health points. The fruit is portable, allowing actors to pick it up
 * and carry it in their inventory. When used, it restores a small amount of health to the actor.
 */
public class LargeFruit extends HealingItem {
    /**
     * Constructor for the Large Fruit item.
     *
     * Initializes a Large Fruit with a specific name and display character on the game map. It is set as
     * portable, meaning it can be picked up and moved by actors. The healing points are set to a predefined
     * value, indicating how much health it will restore when used.
     */
    public LargeFruit() {
        super("Large Fruit", 'O', true);
        this.healingPoint = 2;
    }

    /**
     * Provides a list of actions that are allowable on this item by the owner actor.
     *
     * This method extends the base allowableActions method to include a healing action that the owner
     * can perform using this item. This allows actors to consume the fruit to regain health.
     *
     * @param owner The actor who owns or is considering using this item.
     * @return An ActionList containing all actions that can be performed with this item, including healing.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
        actions.add(new HealingAction(owner, this));
        return actions;
    }

    /**
     * Returns the amount of healing points this Large Fruit provides.
     *
     * Implements the abstract method from HealingItem to specify the exact amount of health points
     * that consuming the Large Fruit restores.
     *
     * @return The number of health points (healing points) restored by this item.
     */
    @Override
    public int getHealingPoints() {
        return healingPoint;
    }
}
