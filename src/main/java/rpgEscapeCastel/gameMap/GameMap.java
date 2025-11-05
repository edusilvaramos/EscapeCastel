package rpgEscapeCastel.gameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpgEscapeCastel.ascii.PrintAscii;
import rpgEscapeCastel.player.Player;
import rpgEscapeCastel.player.Team;

public final class GameMap {

    // pensar em fazer um ArrayList de de objetos para nanip.... 
    private static final int SIZE = 13;
    private final IPlace[][] grid = new IPlace[SIZE][SIZE];
    private final Player player;
    private static final String DIV_FILE_NAME = "div.txt";

    private int playerRow = 0;
    private int playerCol = 0;

    public GameMap(Player player) {
        this.player = player;
        firstMap();
    }

    private void firstMap() {
        // start
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = new EmptyCell();
            }
        }

        // Exit
        grid[SIZE - 1][SIZE - 1] = new ExitCastel();

        // player
        grid[0][0] = this.player;

        // testes com marcas ****
        List<int[]> free = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!((row == 0 && col == 0) || (row == SIZE - 1 && col == SIZE - 1))) {
                    if (grid[row][col].isFreePlace()) {
                        free.add(new int[]{row, col});
                    }
                }
            }
        }
        // 
        Collections.shuffle(free, new Random());
        int idx = 0;

        // base map size
        int nObstaclerAndMosters = SIZE * SIZE / 12;

        for (int i = 0; i < nObstaclerAndMosters && idx < free.size(); i++, idx++) {
            int[] p = free.get(idx);
            grid[p[0]][p[1]] = Monster.fromTeam(Team.random());
        }
        for (int i = 0; i < nObstaclerAndMosters && idx < free.size(); i++, idx++) {
            int[] p = free.get(idx);
            grid[p[0]][p[1]] = new Obstacle();
        }
    }

    private void printMap() {
        PrintAscii.printAsciiart("castel.txt");
        System.out.println();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                String cell = (grid[r][c].isFreePlace()) ? "   " : grid[r][c].symbolPlace();
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
                break;
            }
            // movimento
            int alvoR = playerRow;
            int alvoC = playerCol;

            // movimentos teclado
            if (move.equals("z")) {
                alvoR--;
            } else if (move.equals("s")) {
                alvoR++;
            } else if (move.equals("q")) {
                alvoC--;
            } else if (move.equals("d")) {
                alvoC++;
            }

            if (alvoR == SIZE - 1 && alvoC == SIZE - 1) {
                ExitCastel.win();
                break;
            }
            if (alvoR < 0 || alvoR >= SIZE || alvoC < 0 || alvoC >= SIZE) {
                System.out.println();
                System.out.println("Out of bounds!!! You can't escape from here!! ha ha ha");
                System.out.println();
                continue;
            }

            IPlace goPlace = grid[alvoR][alvoC];
            boolean moveAllowed = false;

            if (goPlace.isFreePlace()) {
                moveAllowed = true;
            } else {
                // destructible y/n ? 
                IDestructible enemy = goPlace.asDestructible();
                if (enemy == null) {
                    PrintAscii.printAsciiart("div.txt");
                    printMap();
                    continue;
                }

                // if asDestructible go to fight !
                PrintAscii.printAsciiart("fight.txt");
                System.out.println();
                PrintAscii.printAsciiart(player.getTeam().getFileName());
                player.printInventory();
                System.out.println();
                PrintAscii.printAsciiart("x.txt");
                System.out.println();
                PrintAscii.printAsciiart(goPlace.getFileName());
                System.out.println();
                PrintAscii.printAsciiart(DIV_FILE_NAME);

                System.out.println("Fight or chicken out? (f/c)");
                String battle = SC.nextLine().trim().toLowerCase();

                if ("f".equals(battle)) {
                    boolean playerWon = fight(player, enemy);
                    if (playerWon) {
                        ExitCastel.winFight();
                        // get the place he he victory !
                        grid[alvoR][alvoC] = new EmptyCell();
                        moveAllowed = true;
                    } else {
                        ExitCastel.youDied();
                        break;
                    }
                } else {
                    // if chicken out
                    PrintAscii.printAsciiart(DIV_FILE_NAME);
                    System.out.println("Chicken out!! loser... !!");
                    printMap();
                    continue;
                }
            }

            if (moveAllowed) {
                grid[playerRow][playerCol] = new EmptyCell();
                grid[alvoR][alvoC] = player;
                playerRow = alvoR;
                playerCol = alvoC;
            }
            PrintAscii.printAsciiart(DIV_FILE_NAME);
            printMap();

        }
    }

    public boolean fight(Player p, IDestructible enemy) {
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
