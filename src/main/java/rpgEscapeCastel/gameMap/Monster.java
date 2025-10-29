package rpgEscapeCastel.gameMap;

import rpgEscapeCastel.player.Team;

public class Monster implements Destructible, InterfacePlace {

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
        return " M ";
    }
}
