package game.grounds.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface defining the behavior for spawning actors within the game world.
 * <p>
 * This interface provides a contract for implementing actor spawning mechanisms, which can be used
 * by various components of the game to populate the game environment dynamically. It specifies a method
 * for spawning actors at a given location with a specified chance of spawning.
 */
public interface Spawn {
    /**
     * Attempts to spawn an actor at a specified location based on a given probability.
     * <p>
     * This method provides the functionality to place an actor at a particular location in the game map,
     * contingent upon a specified spawn chance. The implementation should handle the probability logic
     * and the actual placement of the actor within the game environment.
     *
     * @param location     The location at which to attempt to spawn the actor.
     * @param actor        The actor to be spawned.
     * @param spawnChance  The percentage chance (0-100) of the actor spawning at the location.
     */
    void spawnActor(Location location, Actor actor, int spawnChance);
}
