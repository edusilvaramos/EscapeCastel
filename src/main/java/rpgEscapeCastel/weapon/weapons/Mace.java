package rpgEscapeCastel.weapon.weapons;

import rpgEscapeCastel.weapon.Weapon;

public final class Mace extends Weapon {

    public Mace() {
        super(6, "Mace", 600, 100, "mace.txt");
    }

    @Override
    public String asciiArt() {
        return loadAscii();
    }
}
