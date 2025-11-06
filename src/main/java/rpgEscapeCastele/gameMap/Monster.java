package rpgEscapeCastele.gameMap;

import rpgEscapeCastele.player.Team;

public class Monster implements IDestructible, IPlace {

    private final String name;
    private int life;
    private final int defense;
    private final String fileName;

    public Monster(String name, int life, int defense, String fileName) {
        this.name = name;
        this.life = life;
        this.defense = defense;
        this.fileName = fileName;
    }

    // make a rondonly moster from team 
    public static Monster fromTeam(Team team) {
        return new Monster(
                team.getTeamName(),
                team.getTeamLife(),
                team.getTeamPower(),
                team.getFileName()
        );
    }

    public String getName() {
        return name;
    }

    @Override
    public void takeDamage(int amount) {
        int effective = Math.max(0, amount - defense);
        life -= effective;
    }

    @Override
    public boolean isDestroyed() {
        return life <= 0;
    }

    @Override
    public String symbolPlace() {
        return "   ";
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getPower() {
        return defense;
    }

    @Override
    public boolean isFreePlace() {
        return false;
    }

    @Override
    public IDestructible asDestructible() {
        return this;
    }
    @Override
    public String toString() {
        return "Monster: " + name + ", Life: " + life + ", Defense: " + defense;
    }

}
