package rpgEscapeCastel.weapon.weapons;

import rpgEscapeCastel.weapon.Weapon;

public final class Knife extends Weapon {

    public Knife() {
        super(5, "Knife", 300, 100, "knives.txt");
    }

    @Override
    public String asciiArt() {
        return loadAscii();
    }
}
