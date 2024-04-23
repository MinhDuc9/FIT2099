package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Represents a Large Bolt item in the game.
 *
 * This item class defines Large Bolts, which can be used within the game for various purposes,
 */
public class LargeBolt extends Item {
    /**
     * Constructor for the LargeBolt item.
     *
     * Initializes a new item with the name "Large Bolts", uses a '+' character for its representation
     * on the game map, and sets it as portable, allowing it to be picked up and moved around by players.
     */
    public LargeBolt() {
        super("Large Bolts", '+', true);
    }
}
