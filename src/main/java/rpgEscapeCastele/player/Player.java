package rpgEscapeCastele.player;

import rpgEscapeCastele.ascii.PrintAscii;
import rpgEscapeCastele.gameMap.IDestructible;
import rpgEscapeCastele.gameMap.IPlace;
import rpgEscapeCastele.weapon.Weapon;

public class Player implements IDestructible, IPlace {

    private String name;
    private final Team team;
    private final Inventory inventory;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.inventory = new Inventory();
    }

    public String getName() {
        return "monsters/" + name;
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

        PrintAscii.printAsciiart(team.getFileName());
        printInventory();
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
            System.out.printf("%d) Name: %s - Damage: %d - Price: %d", i++, w.getName(), w.getDamage(), w.getPrice());
            System.err.println("");
            System.out.println(PrintAscii.loadAsciiart(w.resourcePath()));
            System.err.println("");

        }
        System.out.println("----------------------");

    }

    @Override
    public String symbolPlace() {
        return " P ";
    }

    @Override
    public String getFileName() {
        return team.getFileName();
    }

    @Override
    public boolean isFreePlace() {
        return false;
    }

    @Override
    public void takeDamage(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeDamage'");
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public int getPower() {
        int w = inventory.getWeapon().getDamage();
        return team.getTeamPower() + w;
    }

    @Override
    public int getLife() {
        return team.getTeamLife();
    }

    @Override
    public IDestructible asDestructible() {
        return null;
    }

}
