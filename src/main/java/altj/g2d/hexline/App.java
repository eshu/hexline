package altj.g2d.hexline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length < 7) {
            usage();
            return;
        }
        int width = parse("width", args[1]);
        int height = parse("height", args[2]);
        int x1 = parse("x1", args[3]);
        int y1 = parse("y1", args[4]);
        int x2 = parse("x2", args[5]);
        int y2 = parse("y2", args[6]);
        FileOutputStream fos = new FileOutputStream(args[0]);
        SampleRaster raster = new SampleRaster(width, height);
        SamplePointWriter writer = new SamplePointWriter(new PrintStream(fos), 10, 5, 5);
        writer.init();
        Line line = new Line(new Point(x1, y1), new Point(x2, y2));
        raster.draw(line);
        raster.write(writer);
        writer.close();
        fos.close();
    }

    private static int parse(final String name, final String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            System.err.println("Invalid " + name);
            usage();
            throw e;
        }
    }

    private static void usage() {
        System.err.println("Arguments: filename width height x1 y1 x2 y2");
    }
}
