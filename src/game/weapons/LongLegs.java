package game.weapons;

import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A weapon class representing "Long Legs" used for attacking with a specific style.
 *
 * This weapon simulates an attack using long legs.
 * It provides a specific implementation of the Weapon interface,
 * detailing the damage, attack verb, and the chance to hit.
 */
public class LongLegs implements Weapon {
    @Override
    public int damage() {
        return 1;
    }

    @Override
    public String verb() {
        return "360 kick";
    }

    @Override
    public int chanceToHit() {
        return 25;
    }
}
