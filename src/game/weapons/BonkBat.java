package game.weapons;

import edu.monash.fit2099.engine.weapons.Weapon;

public class BonkBat implements Weapon {
    @Override
    public int damage() {
        return 99;
    }

    @Override
    public String verb() {
        return "Bonk";
    }

    @Override
    public int chanceToHit() {
        return 99;
    }
}
