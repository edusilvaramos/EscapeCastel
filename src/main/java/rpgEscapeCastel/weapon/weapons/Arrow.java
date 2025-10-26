package rpgEscapeCastel.weapon.weapons;
import rpgEscapeCastel.weapon.Weapon;
public final class Arrow extends Weapon {
    public Arrow() { super(1, "Arrow", 100, 100, "arrow.txt"); }
    @Override public String asciiArt() { return loadAscii(); }
}
