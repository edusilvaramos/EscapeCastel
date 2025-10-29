package rpgEscapeCastel.gameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpgEscapeCastel.ascii.PrintAscii;
import rpgEscapeCastel.player.Team;

public final class GameMap {

    private static final int SIZE = 8;
    private final InterfacePlace[][] grid = new InterfacePlace[SIZE][SIZE];
    private final Random rnd = new Random();

    public GameMap(InterfacePlace player) {
        fisrtMap(player); 
    }

    private void fisrtMap(InterfacePlace player) {
        // limpa
        for (int r = 0; r < SIZE; r++) for (int c = 0; c < SIZE; c++) grid[r][c] = null;

        // início e saída
        grid[0][0] = player;
        grid[SIZE - 1][SIZE - 1] = new ExitCastel();

        List<int[]> livres = freeCells();            // where is free cells
        Collections.shuffle(livres, rnd);

        int qtdMonstros = 3, qtdObstaculos = 3;
        int needed = Math.min(qtdMonstros + qtdObstaculos, livres.size());

        int idx = 0;
        // monstros
        for (int i = 0; i < qtdMonstros && idx < needed; i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = Monster.fromTeam(Team.random());
        }
        // obstáculos força a 30 // pra todos hehe 
        for (int i = 0; i < qtdObstaculos && idx < needed; i++, idx++) {
            int[] p = livres.get(idx);
            grid[p[0]][p[1]] = new Obstacle(" O ", 30);
        }
    }

    // where is free cells
    private List<int[]> freeCells() {
        List<int[]> cells = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                boolean isStart = (r == 0 && c == 0);
                boolean isExit  = (r == SIZE - 1 && c == SIZE - 1);
                if (!isStart && !isExit && grid[r][c] == null) {
                    cells.add(new int[]{r, c});
                }
            }
        }
        return cells;
    }

    // show the map
    public void print() {
        PrintAscii.printAsciiart("castel.txt");
        System.out.println();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                String cell = (grid[r][c] == null) ? " ? " : grid[r][c].symbolPlace();
                System.out.print("[" + cell + "]");
            }
            System.out.println();
        }
        System.out.println();
    }
}
