package rpgEscapeCastel.player;

public enum Team {
    CENTAUR(1, "Centaur", 100, 100, 1000, "centaurs.txt"),
    DEVIL(2, "Devil", 100, 100, 1000, "devils.txt"),
    DRAGON(3, "Dragon", 100, 100, 1000, "dragons.txt"),
    FAIRY(4, "Fairy", 100, 100, 1000, "fairies.txt"),
    GHOST(5, "Ghost", 100, 100, 1000, "ghosts.txt"),
    GRIM_REAPER(6, "Grim", 100, 100, 1000, "grim.txt"),
    GRYPHON(7, "Gryphon", 100, 100, 1000, "gryphon.txt"),
    MERMAID(8, "Mermaid", 100, 100, 1000, "mermaids.txt"),
    MONSTER(9, "Monster", 100, 100, 1000, "monsters.txt"),
    OGRE(10, "Ogre", 100, 100, 1000, "ogre.txt"),
    SKELETON(11, "Skeleton", 100, 100, 1000, "skeletons.txt"),
    UNICORN(12, "Unicorn", 100, 100, 1000, "unicorns.txt");

    private final int id;
    private final String teamName;
    private final int teamPower;
    private final int teamLife;
    private final int teamMoney;
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

    public String getFileName() {
        return fileName;
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
}
