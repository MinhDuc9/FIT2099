package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HuntsmanSpider;
import game.grounds.spawner.Spawn;
import game.grounds.spawner.Spawner;

/**
 * A ground type that has the capability to spawn Huntsman Spiders.
 * <p>
 * Crater acts as both a type of terrain and as an actor spawner. It periodically attempts to spawn
 * Huntsman Spiders based on a defined chance during the game's tick updates. This class encapsulates
 * spawning behavior by delegating the actual spawning logic to a Spawner instance.
 */
public class Crater extends Ground implements Spawn {
    /**
     * Constructs a Crater object with a specific display character.
     * <p>
     * Initializes the crater with a 'u' character to represent it on the game map and sets up the spawner.
     */
    private final Spawner spawner;

    public Crater() {
        super('u');
        this.spawner = new Spawner();
    }

    /**
     * Called once per turn, allowing the Crater to perform actions such as spawning actors.
     * <p>
     * This method overrides the tick method to integrate actor spawning each game tick. Specifically,
     * it attempts to spawn a Huntsman Spider at the crater's location with a 5% chance.
     *
     * @param location The location of the Crater on the game map.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.spawnActor(location, new HuntsmanSpider(), 5);
    }

    /**
     * Spawns an actor at the specified location using the Spawner instance if the random chance succeeds.
     * <p>
     * This method delegates the spawning of an actor to the Spawner class, which handles the logic to
     * determine if the actor should be spawned based on the provided spawn chance.
     *
     * @param location    The location where the actor may be spawned.
     * @param actor       The actor to spawn, typically a Huntsman Spider.
     * @param spawnChance The chance of the actor spawning at the location, expressed as a percentage.
     */
    @Override
    public void spawnActor(Location location, Actor actor, int spawnChance) {
        spawner.spawnActor(location, actor, spawnChance);
    }
}
