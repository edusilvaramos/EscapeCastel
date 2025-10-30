package rpgEscapeCastel.gameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpgEscapeCastel.ascii.PrintAscii;
import rpgEscapeCastel.player.Player;
import rpgEscapeCastel.player.Team;

public final class GameMap {

    private static final int SIZE = 8;
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

        // coloca 3 M e 3 O em lugares livres
        List<int[]> livres = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (!((r == 0 && c == 0) || (r == SIZE - 1 && c == SIZE - 1))) {
                    if (grid[r][c] == null) {
                        livres.add(new int[]{r, c});
                    }
                }
            }
        }
        Collections.shuffle(livres, rnd);
        int idx = 0;

        for (int i = 0; i < 3 && idx < livres.size(); i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = Monster.fromTeam(Team.random());
        }
        for (int i = 0; i < 3 && idx < livres.size(); i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = new Obstacle();
        }
    }

    private void print() {
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
        PrintAscii.printAsciiart("div.txt");
    }

    // se o espertinho sair do mapa
    private boolean isInside(int r, int c) {
        return r >= 0 && r < SIZE && c >= 0 && c < SIZE;
    }

    private static final java.util.Scanner SC = new java.util.Scanner(System.in);

    public void playerMovement() {
        print();
        while (true) {
            System.out.println("Move: ");
            System.out.println("up: z");
            System.out.println("down: s");
            System.out.println("left: q");
            System.out.println("right: d");
            System.out.println("To be a loser hehe: x");
            System.out.println();
            System.out.print("What do you want to do? ");
            String cmd = SC.nextLine().trim().toLowerCase();

            if (cmd.equals("x")) {
                ExitCastel.youDied();
                System.exit(0);
            }

            int alvoR = playerRow;
            int alvoC = playerCol;

            // movimento simples
            if (cmd.equals("z")) {
                alvoR--;
            } else if (cmd.equals("s")) {
                alvoR++;
            } else if (cmd.equals("q")) {
                alvoC--;
            } else if (cmd.equals("d")) {
                alvoC++;
            }

            if (!isInside(alvoR, alvoC)) {
                System.out.println("");
                System.out.println("Out of bounds !!!");
                System.out.println("");

                continue;
            }

            InterfacePlace goPlace = grid[alvoR][alvoC];
            if ((goPlace instanceof Monster) || (goPlace instanceof Obstacle)) {

                PrintAscii.printAsciiart("fight.txt");
                System.out.println("");
                PrintAscii.printAsciiart(player.getTeam().getFileName());
                System.out.println("");
                PrintAscii.printAsciiart("x.txt");
                System.out.println("");
                PrintAscii.printAsciiart(goPlace.getFileName());
                System.out.println();
                PrintAscii.printAsciiart("div.txt");

                System.out.println("Fight or chicken out? (f/c)");

                System.out.println();
                System.out.print("What do you want to do? ");
                String cmd2 = SC.nextLine().trim().toLowerCase();

                if (cmd2.equals("f")) {
                    if (!fighth(player, (Destructible) goPlace)) {
                        ExitCastel.youDied();
                        System.exit(0);
                    }else {
                        ExitCastel.winFight();
                    }
                }
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
            print();
        }
    }

    public boolean fighth(Player p, Destructible enemy) {

        // power = weaponPower + teamPower
        Integer weaponDamage = p.getInventory().getWeapon().getDamage();
        if (weaponDamage < 0) {
            weaponDamage = 0;
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
