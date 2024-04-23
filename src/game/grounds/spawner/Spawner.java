package game.grounds.spawner;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the spawning of actors at a location with a specified probability.
 * <p>
 * This class provides detailed functionality for randomly spawning actors around a specified location.
 * It considers only those adjacent locations that do not block thrown objects for potential spawning points,
 * and ensures that no actor is already present at the spawning location.
 */
public class Spawner implements Spawn {
    /**
     * Spawns an actor at an available location near the specified location based on a given chance.
     * <p>
     * The method first generates a list of possible spawn locations from the exits of the specified
     * location that do not block thrown objects. It then filters out any locations that already have an actor.
     * If there are viable locations and the randomly generated number is less than the spawnChance,
     * the actor is added to one of these locations.
     *
     * @param location     The central location around which to check for viable spawn points.
     * @param actor        The actor to potentially spawn.
     * @param spawnChance  The probability (expressed as a percentage from 0 to 100) that the actor will spawn.
     */
    @Override
    public void spawnActor(Location location, Actor actor, int spawnChance) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        List<Location> spawnLocations = new ArrayList<>(location.getExits().stream()
                .map(Exit::getDestination)
                .filter(v -> !v.getGround().blocksThrownObjects())
                .toList());
        spawnLocations.removeIf(spawnLocation -> spawnLocation.getActor() != null);

        if (!spawnLocations.isEmpty() && randomNumber < spawnChance) {
            Location chosenLocation = spawnLocations.get(random.nextInt(spawnLocations.size()));
            chosenLocation.addActor(actor);
        }
    }
}
