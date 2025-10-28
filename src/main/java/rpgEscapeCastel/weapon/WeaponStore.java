package rpgEscapeCastel.weapon;

import java.util.ArrayList;
import java.util.List;

import rpgEscapeCastel.player.Player;

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

    public boolean buyWeapon(int idWeapon, Player player) {
        int weaponValue = Weapon.fromId(idWeapon).getPrice();
        int money = player.getTeam().getTeamMoney();
        if (money < weaponValue) {
            return false;
        }
        player.getTeam().setTeamMoney(money - weaponValue);
        player.getInventory().addWeapon(Weapon.fromId(idWeapon));
        return true;
    }

    public void removeWeapon(int idWeapon) {

        this.stockWeapons.removeIf(w -> w.getId() == idWeapon);
    }

    public void sellWeapon(int idWeapon) {
        this.stockWeapons.add(Weapon.fromId(idWeapon));
    }

}
