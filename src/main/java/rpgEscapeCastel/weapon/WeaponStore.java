package rpgEscapeCastel.weapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponStore {

    private final List<Weapon> stockWeapons = new ArrayList<>();

    public WeaponStore() {
        for (int id = 1; id <= 8; id++) {
            stockWeapons.add(Weapon.fromId(id));
        }
    }

    public void openStock() {
        System.out.println("=== Welcome to Weapon Store !!===");
        System.out.println("== Weapons ==");
        for (Weapon w : stockWeapons) {
           System.out.println(w.getId() + ") " + w.getName() + " - Damage: " + w.getDamage() + " - Price: " + w.getPrice());
        }
        System.out.println("==================================");

    }

    public void buyWeapon(int idWeapon) {
        this.stockWeapons.removeIf(w -> w.getId() == idWeapon);
    }

    public void sellWeapon(int idWeapon) {
        this.stockWeapons.add(Weapon.fromId(idWeapon));
    }

}
