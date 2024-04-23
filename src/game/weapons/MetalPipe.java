package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;

/**
 * A weapon class representing a Metal Pipe.
 *
 * This class extends WeaponItem and provides functionality for a Metal Pipe weapon used within the game.
 * It is characterized by specific damage output, attack verb, and hit chance. The Metal Pipe can be used
 * to perform an attack action, and this class defines how actors can interact with the weapon.
 */
public class MetalPipe extends WeaponItem {
    /**
     * Constructor for the Metal Pipe weapon.
     *
     * Initializes a Metal Pipe with the name "Metal Pipe", uses a '!' character for its representation on the game map,
     * and sets basic combat attributes such as damage, attack verb, and hit chance.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', 1, "toy smash", 20);
    }

    /**
     * Performs a periodic action associated with the Metal Pipe.
     * This method is called once per turn if the weapon is equipped by an actor. It currently defers
     * to the superclass implementation, which might include general maintenance or effect processing
     * for the weapon while it is equipped.
     *
     * @param currentLocation The location of the actor carrying the weapon.
     * @param actor The actor carrying the weapon.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**
     * Provides a list of actions that are allowable on this weapon by the owner actor.
     *
     * This method extends the base allowableActions method to include an attack action that can be performed using this weapon.
     * It allows the owner to initiate an attack using the Metal Pipe, incorporating the weapon's specific attributes.
     *
     * @param owner The actor who owns or is considering using this weapon.
     * @param location The current location of the actor, included in the action description.
     * @return An ActionList containing all actions that can be performed with this weapon, including attack actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, Location location) {
        ActionList actions = super.allowableActions(owner);

        actions.add(new AttackAction(owner, location.toString(),this));

        return actions;
    }
}
