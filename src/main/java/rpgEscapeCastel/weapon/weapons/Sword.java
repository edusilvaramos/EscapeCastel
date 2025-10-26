package rpgEscapeCastel.weapon.weapons;
import rpgEscapeCastel.weapon.Weapon;
public final class Sword extends Weapon {
    public Sword() { super(8, "Sword", 400, 500, "swords.txt"); }
    @Override public String asciiArt() { return loadAscii(); }
}
