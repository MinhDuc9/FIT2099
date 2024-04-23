package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.HealingAction;

/**
 * Represents a Small Fruit item that actors can consume for minor healing in the game.
 *
 * This class extends HealingItem to provide a specific healing item, the Small Fruit, which
 * can be used by actors to restore a minimal amount of health points. The fruit is portable, allowing actors to pick it up
 * and carry it in their inventory. When used, it restores health, albeit less than its larger counterpart.
 */
public class SmallFruit extends HealingItem {
    /**
     * Constructor for the Small Fruit item.
     *
     * Initializes a Small Fruit with a specific name and display character on the game map. It is set as
     * portable, meaning it can be picked up and moved by actors. The healing points are set to a lower value,
     * indicating how much health it will restore when used.
     */
    public SmallFruit() {
        super("Small Fruit", 'o', true);
        this.healingPoint = 1;
    }

    /**
     * Provides a list of actions that are allowable on this item by the owner actor.
     *
     * This method extends the base allowableActions method to include a healing action that the owner
     * can perform using this item. This allows actors to consume the fruit to regain a small amount of health.
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
     * Returns the amount of healing points this Small Fruit provides.
     *
     * Implements the abstract method from HealingItem to specify the exact amount of health points
     * that consuming the Small Fruit restores.
     *
     * @return The number of health points (healing points) restored by this item.
     */
    @Override
    public int getHealingPoints() {
        return healingPoint;
    }
}
