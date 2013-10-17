package altj.g2d.polygram;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class App {
    private static final double radius = 100;
    private static final double textRadius = 120;

    private static final double offsetX = 150;
    private static final double offsetY = 150;

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(args[0]);
        ShapeWriter writer = new ShapeWriter(new PrintStream(fos), offsetX, offsetY);
        writer.init();
        Polygram.write(writer, 5, 2, radius, textRadius, false);
        writer = new ShapeWriter(new PrintStream(fos), 3 * offsetX, offsetY);
        Polygram.write(writer, 5, 2, radius, textRadius, true);
        writer = new ShapeWriter(new PrintStream(fos), offsetX, 3 * offsetY);
        Polygram.write(writer, 9, 4, radius, textRadius, false);
        writer = new ShapeWriter(new PrintStream(fos), 3 * offsetX, 3 * offsetY);
        Polygram.write(writer, 9, 4, radius, textRadius, true);
        writer.close();
    }
}
