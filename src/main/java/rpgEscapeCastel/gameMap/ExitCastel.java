package rpgEscapeCastel.gameMap;

import rpgEscapeCastel.ascii.PrintAscii;

public final class ExitCastel implements InterfacePlace {

    @Override
    public String symbolPlace() {
        return "-> ";
    }

    public static void gameOver() {
        PrintAscii.printAsciiart("logo/gameOver.txt");

    }

    public static void youDied() {
        PrintAscii.printAsciiart("logo/youDied.txt");
    }

    public static void win() {
        PrintAscii.printAsciiart("logo/win.txt");
    }

}
