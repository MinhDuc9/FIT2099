package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

/**
 * Represents a player character in the game.
 * <p>
 * This class is an extension of the Actor class specifically tailored to represent a player-controlled character.
 * It includes methods that handle the turn-based actions of the player, the player's intrinsic weapon capabilities,
 * and other player-specific properties and behaviors such as being hostile to enemies.
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Executes a turn for the Player.
     *
     * This method handles the logic for player actions each turn. It supports the continuation of multi-turn actions
     * and displays a menu of available actions for the player to choose from. The method ensures that any subsequent
     * actions from the last action (if any) are continued before showing the menu.
     *
     * @param actions    The list of possible actions this Player can take this turn.
     * @param lastAction The action this Player took last turn, used to check for any subsequent actions.
     * @param map        The game map on which the player is located.
     * @param display    The display used to interact with the player.
     * @return The action chosen by the player, either a continuation of the last action or a new one selected from the menu.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        System.out.println(this.name);
        System.out.println("HP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Returns the intrinsic weapon of the player.
     * <p>
     * This method defines the player's default weapon used when no other weapons are equipped.
     * The weapon has a low damage but comes with a unique descriptive attack verb.
     *
     * @return an instance of IntrinsicWeapon representing the player's natural combat abilities.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1, "100 tons punch", 5);
    }
}
