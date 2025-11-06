package rpgEscapeCastele.player;

import java.util.concurrent.ThreadLocalRandom;

public enum Team {
    CENTAUR(1, "Centaur", 25, 180, 60, "centaurs.txt"),
    DEVIL(2, "Devil", 70, 500, 300, "devils.txt"),
    DRAGON(3, "Dragon", 90, 650, 500, "dragons.txt"),
    FAIRY(4, "Fairy", 12, 80, 20, "fairies.txt"),
    GHOST(5, "Ghost", 15, 90, 25, "ghosts.txt"),
    GRIM_REAPER(6, "Grim", 85, 600, 450, "grim.txt"),
    GRYPHON(7, "Gryphon", 35, 260, 90, "gryphon.txt"),
    MERMAID(8, "Mermaid", 22, 140, 50, "mermaids.txt"),
    MONSTER(9, "Monster", 20, 120, 40, "monsters.txt"),
    OGRE(10, "Ogre", 40, 300, 120, "ogre.txt"),
    SKELETON(11, "Skeleton", 10, 60, 15, "skeletons.txt"),
    UNICORN(12, "Unicorn", 45, 320, 150, "unicorns.txt");

    private final int id;
    private final String teamName;
    private final int teamPower;
    private final int teamLife;
    private int teamMoney;
    private final String fileName;

    Team(int id, String teamName, int teamPower, int teamLife, int teamMoney, String fileName) {
        this.id = id;
        this.teamName = teamName;
        this.teamPower = teamPower;
        this.teamLife = teamLife;
        this.teamMoney = teamMoney;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamPower() {
        return teamPower;
    }

    public int getTeamLife() {
        return teamLife;
    }

    public int getTeamMoney() {
        return teamMoney;
    }

    public int setTeamMoney(int teamMoney) {
        this.teamMoney = teamMoney;
        return this.teamMoney;
    }

    public String getFileName() {
        return "monsters/" + fileName;
    }

    // lista simples (id - nome)
    public static void printTeams() {
        System.out.println("Choose your team:");
        for (Team t : values()) {
            System.out.println(t.id + " - " + t.teamName);
        }
    }

    public static Team fromId(int id) {
        for (Team t : values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    public static Team random() {
        Team[] all = values();
        return all[ThreadLocalRandom.current().nextInt(all.length)];
    }

}
