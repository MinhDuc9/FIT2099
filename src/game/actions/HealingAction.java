package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.HealingItem;

/**
 * An action that allows an actor to use a healing item to restore health.
 * <p>
 * This class handles the mechanics of using a healing item, including updating the actor's health
 * and managing the inventory to remove the used item. The action is performed within the context of
 * a game map, although the map is not directly manipulated by this action.
 */
public class HealingAction extends Action {
    /**
     * The actor who will use the healing item.
     */
    private final Actor user;

    /**
     * The healing item to be used.
     */
    private final HealingItem healingItem;

    /**
     * Constructs a new HealingAction.
     *
     * @param user The actor who will use the healing item.
     * @param healingItem The healing item that will be used to restore health.
     */
    public HealingAction(Actor user, HealingItem healingItem) {
        this.user = user;
        this.healingItem = healingItem;
    }

    /**
     * Executes the healing action, applying the healing effects of the item to the user.
     *
     * This method increases the health of the actor using the specified healing item and then
     * removes the item from the actor's inventory. A message is returned that describes the action's outcome.
     *
     * @param actor The actor performing the action, typically the same as the user.
     * @param map The game map on which the action is occurring, not directly used in this action.
     * @return A string describing the action's outcome, indicating the actor has been healed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        user.heal(healingItem.getHealingPoints());
        user.removeItemFromInventory(healingItem);

        return actor + " is healed";
    }

    /**
     * Provides a description of the action for display in a menu.
     *
     * This method returns a string that describes the healing action, including the name of the healing item
     * and the amount of health it restores, which can be useful in user interfaces where players choose actions.
     *
     * @param actor The actor performing the action.
     * @return A string description of the healing action, detailing the item and its healing properties.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + healingItem + " can heal: " + healingItem.getHealingPoints();
    }
}
