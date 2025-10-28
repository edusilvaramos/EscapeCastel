package rpgEscapeCastel.ascii;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class PrintAscii {

    private static final String BASE = "ascii/";

    private PrintAscii() {
        
    }
    public static void printAsciiart(String fileName) {
        System.out.print(loadAsciiart(fileName));
    }

    public static String loadAsciiart(String fileName) {
        String path = BASE + Objects.requireNonNull(fileName);
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream in = (cl != null) ? cl.getResourceAsStream(path) : null;
            if (in == null) {
                in = PrintAscii.class.getClassLoader().getResourceAsStream(path);
            }
            if (in == null) {
                in = PrintAscii.class.getResourceAsStream("/" + path);
            }
            if (in == null) {
                return "(missing " + path + ")";
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                return sb.toString();
            }
        } catch (Exception e) {
            return "(error ";
        }
    }
}
