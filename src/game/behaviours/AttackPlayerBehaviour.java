package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actions.AttackAction;

/**
 * A behaviour for actors to attack players or other hostile entities within reach.
 * <p>
 * This behaviour allows an actor to identify and attack other actors that have the capability marked
 * as HOSTILE_TO_ENEMY within adjacent locations on the map. The attack is performed using a specified weapon.
 */
public class AttackPlayerBehaviour implements Behaviour {
    /**
     * The weapon used when executing an attack.
     */
    private final Weapon weapon;

    /**
     * Constructs an AttackPlayerBehaviour with a specific weapon.
     *
     * @param weapon The weapon to use when performing attacks.
     */
    public AttackPlayerBehaviour(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Determines the action to be taken based on this behaviour, specifically targeting players or hostile actors.
     * <p>
     * Scans all adjacent locations to the actor's current position on the map. If another actor
     * is present in these locations and is marked as hostile to the enemy, an attack action is initiated
     * against that actor using the specified weapon.
     *
     * @param actor The actor implementing this behaviour.
     * @param map   The game map on which the actor is located, used to check adjacent locations.
     * @return An Action that the actor can perform this turn, specifically an AttackAction if a target is found; otherwise, null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor target = destination.getActor();
            if (target != null) {
                if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(target, weapon);
                }
            }
        }

        return null;
    }
}
