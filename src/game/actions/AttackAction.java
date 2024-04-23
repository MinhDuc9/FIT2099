package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * An action class that represents an attack by one actor upon another using a weapon.
 * <p>
 * This class handles the logic to perform an attack action within a game, including determining
 * if the attack hits and calculating damage if it does. The action also supports attacks with both
 * specific weapons and the actor's intrinsic weapon.
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon the weapon used to perform the attack
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructs an attack action with a specified weapon but no direction.
     *
     * @param target the Actor to attack
     * @param weapon the weapon used by the actor
     */
    public AttackAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack action.
     *
     * This method calculates whether the attack hits based on the weapon's chance to hit and then
     * applies damage to the target if successful. If the target is rendered unconscious by the attack,
     * additional effects can occur as defined by the target's {@code unconscious} method.
     *
     * @param actor the actor performing the attack
     * @param map the game map on which the attack is happening
     * @return a string describing the outcome of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Provides a string describing the action, suitable for displaying in a menu.
     *
     * @param actor the actor performing the action
     * @return a string description of the attack, including the target and weapon used
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
