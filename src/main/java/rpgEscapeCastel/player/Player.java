package rpgEscapeCastel.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rpgEscapeCastel.weapon.Weapon;

public class Player {

    private String name;
    private final Team team;
    private final Inventory inventory;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void printAvatar() {
        System.out.println("----------- Avatar -----------");
        System.out.println("Player: " + name);
        System.out.println("Team:   " + team.getTeamName());
        System.out.println("Power:  " + team.getTeamPower());
        System.out.println("Life:   " + team.getTeamLife());
        System.out.println("Money:  " + team.getTeamMoney());
        System.out.println();

        String path = "/ascii/monsters/" + team.getFileName();
        try (InputStream in = Player.class.getResourceAsStream(path)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            printInventory();
        } catch (IOException e) {
            System.out.println("(errro");
        }
    }

    public void printInventory() {
        System.out.println("----------- Inventory -----------");
        int used = inventory.size();
        int cap = inventory.getCapacity();
        System.out.printf("Slots: %d/%d%n", used, cap);

        if (used > cap) {
            System.out.println("Inventory is full");
            return;
        }

        if (inventory.viewWeapons().isEmpty()) {
            System.out.println("Inventory is empty");
        }

        int i = 1;
        for (Weapon w : inventory.viewWeapons()) {
            System.out.printf("%d) Name: %s - Damage: %d - Price: %d", i++,w.getName(), w.getDamage(), w.getPrice());
            System.err.println("");
            System.out.println(w.asciiArt());
            System.err.println("");
            
        }
        System.out.println("----------------------");

    }
}
