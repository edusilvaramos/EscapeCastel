package rpgEscapeCastel.ascii;

import java.io.InputStream;

public final class PrintAscii {

    private static final String BASE = "ascii/";

    private PrintAscii() {

    }

    public static void printAsciiart(String fileName) {
        System.out.print(loadAsciiart(fileName));
    }

    public static String loadAsciiart(String fileName) {
        String path = BASE + fileName;
        try (InputStream in = PrintAscii.class.getClassLoader().getResourceAsStream(path)) {
            int i = in.read();
            while (i != -1) {
                System.out.print((char) i);
                i = in.read();
            }
            System.err.println("");
            in.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new StringBuilder().toString();

    }
}
