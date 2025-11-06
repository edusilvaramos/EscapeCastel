package rpgEscapeCastele.gameMap;

import rpgEscapeCastele.ascii.PrintAscii;

public final class ExitCastele implements  IPlace{

    @Override
    public String symbolPlace() {
        return "-> ";
    }

    public String getFileName() {
        return "exit.txt";
    }

    public static void gameOver() {
        PrintAscii.printAsciiart("logo/gameOver.txt");
        System.exit(0);
    }

    public static void youDied() {
        PrintAscii.printAsciiart("logo/youDied.txt");
        System.exit(0);
    }

    public static void win() {
        PrintAscii.printAsciiart("logo/win.txt");
    }
    public static void winFight() {
        PrintAscii.printAsciiart("logo/winfight.txt");
    }

    @Override
    public boolean isFreePlace() {
        return false;
    }

    @Override
    public IDestructible asDestructible() {
        return null;
    }

}
