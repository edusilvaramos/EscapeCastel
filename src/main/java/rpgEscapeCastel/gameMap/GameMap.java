package rpgEscapeCastel.gameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpgEscapeCastel.ascii.PrintAscii;
import rpgEscapeCastel.player.Player;
import rpgEscapeCastel.player.Team;

public final class GameMap {

    private static final int SIZE = 13;
    private final InterfacePlace[][] grid = new InterfacePlace[SIZE][SIZE];
    private final Random rnd = new Random();
    private Player player;

    private int playerRow = 0;
    private int playerCol = 0;

    public GameMap(InterfacePlace player) {
        fisrtMap(player);
        this.player = (Player) player;
    }

    private void fisrtMap(InterfacePlace player) {
        // start
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = null;
            }
        }

        // player e saída
        grid[0][0] = player;
        playerRow = 0;
        playerCol = 0;
        grid[SIZE - 1][SIZE - 1] = new ExitCastel();

        // apor testes deixar eles sem marcas 
        // coloca 3 M e 3 O em lugares livres
        List<int[]> livres = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!((row == 0 && col == 0) || (row == SIZE - 1 && col == SIZE - 1))) {
                    if (grid[row][col] == null) {
                        livres.add(new int[]{row, col});
                    }
                }
            }
        }
        Collections.shuffle(livres, rnd);
        int idx = 0;

        // base map size
        int nObstaclerAndMosters = SIZE * SIZE / 12;

        for (int i = 0; i < nObstaclerAndMosters && idx < livres.size(); i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = Monster.fromTeam(Team.random());
        }
        for (int i = 0; i < nObstaclerAndMosters && idx < livres.size(); i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = new Obstacle();
        }
    }

    private void printMap() {
        PrintAscii.printAsciiart("castel.txt");
        System.out.println();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                String cell = (grid[r][c] == null) ? "   " : grid[r][c].symbolPlace();
                System.out.print("[" + cell + "]");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    private static final java.util.Scanner SC = new java.util.Scanner(System.in);

    public void playerMovement() {
        printMap();
        while (true) {
            System.out.println("Move: ");
            System.out.println("up: z");
            System.out.println("down: s");
            System.out.println("left: q");
            System.out.println("right: d");
            System.out.println("To be a loser hehe: x");
            System.out.println();
            System.out.print("What do you want to do? ");
            String move = SC.nextLine().trim().toLowerCase();

            if (move.equals("x")) {
                ExitCastel.youDied();
                System.exit(0);
            }
            // possit
            int alvoR = playerRow;
            int alvoC = playerCol;

            // movimento simples
            if (move.equals("z")) {
                alvoR--;
            } else if (move.equals("s")) {
                alvoR++;
            } else if (move.equals("q")) {
                alvoC--;
            } else if (move.equals("d")) {
                alvoC++;
            }

            if (alvoR < 0 || alvoR >= SIZE || alvoC < 0 || alvoC >= SIZE) {
                System.out.println();
                System.out.println("Out of bounds!!! You can't escape from here!! ha ha ha");
                System.out.println();
                continue;
            }

            InterfacePlace goPlace = grid[alvoR][alvoC];

            // refac this, x instanceof...
            if ((goPlace instanceof Monster) || (goPlace instanceof Obstacle)) {

                PrintAscii.printAsciiart("fight.txt");
                System.out.println("");
                PrintAscii.printAsciiart(player.getTeam().getFileName());
                player.printInventory();
                System.out.println("");
                PrintAscii.printAsciiart("x.txt");
                System.out.println("");
                PrintAscii.printAsciiart(goPlace.getFileName());
                System.out.println();
                PrintAscii.printAsciiart("div.txt");

                System.out.println("Fight or chicken out? (f/c)");
                System.out.println();
                String battle = SC.nextLine().trim().toLowerCase();

                if (battle.equals("f")) {
                    if (!fight(player, (Destructible) goPlace)) {
                        ExitCastel.youDied();
                        break;
                    } else {
                        ExitCastel.winFight();
                    }
                }
                printMap();
                continue;
            }

            // move !
            InterfacePlace player = grid[playerRow][playerCol];
            grid[playerRow][playerCol] = null;
            grid[alvoR][alvoC] = player;
            playerRow = alvoR;
            playerCol = alvoC;

            // checa saída
            if (goPlace instanceof ExitCastel) {
                ExitCastel.win();
                System.exit(0);
            }
            PrintAscii.printAsciiart("div.txt");
            printMap(); 
            
        }
       
    }

    public boolean fight(Player p, Destructible enemy) {

        // power = weaponPower + teamPower
        int weaponDamage = 0;
        if (p.getInventory().getWeapon() == null) {
            weaponDamage = 0;
        } else {
            weaponDamage = p.getInventory().getWeapon().getDamage();
        }

        int playerPower = weaponDamage + p.getTeam().getTeamPower();
        int playerLife = p.getTeam().getTeamLife();
        int enemyLife = enemy.getLife();
        int enemyPower = enemy.getPower();

        // if playerPower > enemyPower -> player atack first
        // if playerPower < enemyPower -> enemy atack first
        boolean playerTurn = false;
        if (playerPower >= enemyPower) {
            playerTurn = true;
        } else if (playerPower < enemyPower) {
            playerTurn = false;
        }
        while (playerLife > 0 && enemyLife > 0) {
            if (playerTurn) {
                enemyLife -= Math.max(1, playerPower);
                if (enemyLife <= 0) {
                    break;
                }
                playerLife -= Math.max(1, enemyPower);
            } else {
                playerLife -= Math.max(1, enemyPower);
                if (playerLife <= 0) {
                    break;
                }
                enemyLife -= Math.max(1, playerPower);
            }
        }

        return playerLife > 0;
    }
}
