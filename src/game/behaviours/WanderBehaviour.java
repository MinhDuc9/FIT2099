package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.Status;

/**
 * Behaviour that allows an actor to wander randomly around the game map.
 * <p>
 * This class implements a wandering behavior for actors, where they move randomly to one of the accessible adjacent
 * locations. It checks if the destination locations are suitable for the actor to enter and ensures they do not
 * wander into areas restricted by specific capabilities like NO_WALKING.
 */
public class WanderBehaviour implements Behaviour {
    /**
     * Random generator for selecting one of the possible exits.
     */
    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor) && !destination.getGround().hasCapability(Status.NO_WALKING)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

    }
}
