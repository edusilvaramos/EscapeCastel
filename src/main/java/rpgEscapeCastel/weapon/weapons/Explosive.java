package rpgEscapeCastel.weapon.weapons;

import rpgEscapeCastel.weapon.Weapon;

public final class Explosive extends Weapon {

    public Explosive() {
        super(3, "Explosive", 500, 400, "explosives.txt");
    }

    @Override
    public String asciiArt() {
        return loadAscii();
    }
}
