package rpgEscapeCastele.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import rpgEscapeCastele.weapon.Weapon;

public final class Inventory {

    private static final int CAPACITY = 1;
    private final List<Weapon> weapons = new ArrayList<>(CAPACITY);

    public boolean addWeapon(Weapon weapon) {
        Objects.requireNonNull(weapon, "weapon");
        if (weapons.size() >= CAPACITY) {
            return false;
        }
        return weapons.add(weapon);
    }

    public boolean removeWeapon(Weapon weapon) {
        return weapons.remove(weapon);
    }

    public List<Weapon> viewWeapons() {
        return Collections.unmodifiableList(weapons);
    }

    public int size() {
        return weapons.size();
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public Weapon getWeapon() {
        for (Weapon w : weapons) {
            return w;
        }
        return null;
    }

    // if player has the weapon return true
    public boolean hasWeapon(Weapon weapon) {
        return weapons.contains(weapon);
    }
}
