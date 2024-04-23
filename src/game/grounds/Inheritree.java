package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract base class for tree-like terrain that cannot be entered by actors.
 *
 * This class serves as a base for different types of tree terrain within the game.
 * It provides a common constructor and methods for handling game ticks and actor movement
 * restrictions. Concrete subclasses can extend this class to implement specific tree behaviors
 * and properties.
 */
public abstract class Inheritree extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Inheritree(char displayChar) {
        super(displayChar);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
    }
}
