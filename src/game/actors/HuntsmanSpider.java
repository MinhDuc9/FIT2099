package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.Status;
import game.behaviours.AttackPlayerBehaviour;
import game.behaviours.WanderBehaviour;
import game.weapons.LongLegs;

import java.util.HashMap;
import java.util.Map;

/**
 * A Huntsman Spider actor class, characterized by specific behaviors and actions.
 * <p>
 * This actor class represents a Huntsman Spider within the game, equipped with distinctive behaviors such as attacking
 * players and wandering around the map. The Huntsman Spider uses a specific weapon and interacts based on predefined behaviors.
 */
public class HuntsmanSpider extends Actor {
    /**
     * Maps priority levels to behaviors for the Huntsman Spider.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for Huntsman Spider, setting initial behaviors.
     * <p>
     * Initializes the Huntsman Spider with a name, display character, and hit points.
     * Sets up behaviors such as attacking the player with a specific weapon and wandering.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        this.behaviours.put(1, new AttackPlayerBehaviour(new LongLegs()));
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The huntsman spider can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the valid actions can be performed on to this Actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
