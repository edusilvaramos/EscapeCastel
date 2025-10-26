package rpgEscapeCastel.weapon.weapons;
import rpgEscapeCastel.weapon.Weapon;
public final class Axe extends Weapon {
    public Axe() { super(2, "Axe", 600, 500, "axes.txt"); }
    @Override public String asciiArt() { return loadAscii(); }
}
