package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.items.LargeFruit;

import java.util.List;
import java.util.Random;

/**
 * A ground type representing a mature tree that can randomly drop fruits around it.
 * <p>
 * This class extends Inheritree and represents a mature tree in the game world. It includes functionality
 * to randomly drop large fruits in adjacent locations that are accessible, simulating fruit falling from the tree.
 */
public class MatureTree extends Inheritree{
    /**
     * Constructor.
     *
     * display character to display for this type of terrain
     */
    public MatureTree() {
        super('T');
    }

    /**
     * Performs a periodic action that potentially results in dropping a large fruit in an adjacent location.
     * <p>
     * This method is called once per turn, giving the tree a chance to drop a fruit nearby.
     * The method determines the adjacent locations that are not blocked and randomly selects one
     * to drop a new Large Fruit into, with a 20% chance each tick.
     *
     * @param location The location of this ground on the game map.
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        Random random = new Random();

        int randomNumber = random.nextInt(100);

        // Get the correct coordinates surround the tree
        List<Location> coordinates = location.getExits().stream()
                .map(Exit::getDestination)
                .filter(v -> !v.getGround().blocksThrownObjects())
                .toList();

        // If the random chance is successful, drop a fruit at a random accessible location
        if (randomNumber < 20) {
            int number = random.nextInt(coordinates.size());
            coordinates.get(number).addItem(new LargeFruit());
        }
    }
}
