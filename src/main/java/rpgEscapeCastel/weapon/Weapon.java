package rpgEscapeCastel.weapon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import rpgEscapeCastel.gameMap.Destructible;
import rpgEscapeCastel.gameMap.Monster;
import rpgEscapeCastel.gameMap.Obstacle;

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
        return "ascii/weapons/" + fileName;
    }

    protected String loadAscii() {
        String path = resourcePath();
        try (var in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                return "(missing " + path + ")";
            }
            try (var br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return "(error reading " + path + ": " + e.getMessage() + ")";
        }
    }

    public abstract String asciiArt();

    public void attack(Destructible target) {
        target.takeDamage(damage);
    }

    public void attack(Monster target) {
        target.takeDamage(damage);
    }

    public void attack(Obstacle target) {
        target.takeDamage(damage);
    }

    // Registry para criar por id (substitui enum.values())
    private static final Map<Integer, Supplier<Weapon>> BY_ID = new HashMap<>();

    static {
        BY_ID.put(1, rpgEscapeCastel.weapon.weapons.Arrow::new);
        BY_ID.put(2, rpgEscapeCastel.weapon.weapons.Axe::new);
        BY_ID.put(3, rpgEscapeCastel.weapon.weapons.Explosive::new);
        BY_ID.put(4, rpgEscapeCastel.weapon.weapons.Gun::new);
        BY_ID.put(5, rpgEscapeCastel.weapon.weapons.Knife::new);
        BY_ID.put(6, rpgEscapeCastel.weapon.weapons.Mace::new);
        BY_ID.put(7, rpgEscapeCastel.weapon.weapons.Shield::new);
        BY_ID.put(8, rpgEscapeCastel.weapon.weapons.Sword::new);
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
        loadAscii();
        return String.format("%d - %s (%d dmg, %d gold)", id, name, damage, price);
    }

}
