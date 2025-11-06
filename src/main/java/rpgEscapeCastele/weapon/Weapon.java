package rpgEscapeCastele.weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import rpgEscapeCastele.ascii.PrintAscii;
import rpgEscapeCastele.gameMap.Monster;
import rpgEscapeCastele.gameMap.Obstacle;

public abstract class Weapon {

    private final int id;
    private final String name;
    private final int price;
    private final int damage;
    private final String fileName;

    protected Weapon(int id, String name, int price, int damage, String fileName) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.price = price;
        this.damage = damage;
        this.fileName = Objects.requireNonNull(fileName);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public String resourcePath() {
        return "weapons/" + fileName;
    }

    public void attack(Monster target) {
        target.takeDamage(damage);
    }

    public void attack(Obstacle target) {
        target.takeDamage(damage);
    }

    // Weapon factory
    private static final Map<Integer, Supplier<Weapon>> BY_ID = new HashMap<>();

    static {
        BY_ID.put(1, rpgEscapeCastele.weapon.weapons.Arrow::new);
        BY_ID.put(2, rpgEscapeCastele.weapon.weapons.Axe::new);
        BY_ID.put(3, rpgEscapeCastele.weapon.weapons.Explosive::new);
        BY_ID.put(4, rpgEscapeCastele.weapon.weapons.Gun::new);
        BY_ID.put(5, rpgEscapeCastele.weapon.weapons.Knife::new);
        BY_ID.put(6, rpgEscapeCastele.weapon.weapons.Mace::new);
        BY_ID.put(7, rpgEscapeCastele.weapon.weapons.Shield::new);
        BY_ID.put(8, rpgEscapeCastele.weapon.weapons.Sword::new);
    }

    public static Weapon fromId(int id) {
        Supplier<Weapon> sup = BY_ID.get(id);
        if (sup == null) {
            throw new IllegalArgumentException("Invalid Weapon id: " + id);
        }
        return sup.get();
    }

    @Override
    public String toString() {
        PrintAscii.printAsciiart(resourcePath());
        return String.format("%d - %s (%d dmg, %d gold)", id, name, damage, price);
    }

}
