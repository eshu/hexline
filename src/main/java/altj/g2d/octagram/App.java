package altj.g2d.octagram;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class App {
    private static final double radius = 100;

    private static final double offsetX = 500;
    private static final double offsetY = 500;

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(args[0]);
        ShapeWriter writer = new ShapeWriter(new PrintStream(fos), offsetX, offsetY);
        writer.init();
        //
        // writeSquares(fos);
        // writeOctagrams(fos);
        //
        // writeHexagrams(fos);
        //
        writePentagrams(fos);
        writer.close();
        fos.close();
    }

    private static void writePentagrams(OutputStream os) {
        final double length = radius * Math.sqrt(2 - 2 * Math.cos(Math.PI / 5));
        final double bigRadius = length + radius;
        for (int i = 0; i < 5; i++) {
            final double bigAngle = 2 * i * Math.PI / 5;
            final double centerX = offsetX + bigRadius * Math.sin(bigAngle);
            final double centerY = offsetY - bigRadius * Math.cos(bigAngle);
            final ShapeWriter writer = new ShapeWriter(new PrintStream(os), centerX, centerY);
            Polygon.write(writer, 5, radius, 2 * i * Math.PI / 5 + Math.PI);
        }
        final double veryBigRadius = bigRadius + radius;
        for (int i = 0; i < 5; i++) {
            final double bigAngle = 2 * i * Math.PI / 5;
            final double centerX = offsetX + veryBigRadius * Math.sin(bigAngle);
            final double centerY = offsetY + veryBigRadius * Math.cos(bigAngle);
            final ShapeWriter writer = new ShapeWriter(new PrintStream(os), centerX, centerY);
            Polygon.write(writer, 5, radius, 2 * i * Math.PI / 5);
        }
    }

    private static void writeHexagrams(OutputStream os) {
        final double bigRadius = 2 * radius;
        final double innerBigRadius = bigRadius * Math.sqrt(3) / 2;
        for (int j = 0; j < 7; j++) {
            final double bigCenterX = offsetX + (bigRadius + radius) * j;
            for (int i = 0; i < 7; i++) {
                final double bigCenterY = offsetY - 2 * innerBigRadius * i - (j % 2) * innerBigRadius;
                for (int k = 0; k < 6; k++) {
                    final double bigAngle = k * Math.PI / 3;
                    final double centerX = bigCenterX + bigRadius * Math.cos(bigAngle);
                    final double centerY = bigCenterY - bigRadius * Math.sin(bigAngle);
                    final ShapeWriter writer = new ShapeWriter(new PrintStream(os), centerX, centerY);
                    Polygon.write(writer, 6, radius, Math.PI / 6);
                }
            }
        }
    }

    private static void writeSquares(OutputStream os) {
        final double bigRadius = radius * (1 + Math.sqrt(2));
        for (int i = 0; i < 8; i++) {
            final double angle = Math.PI * i / 4;
            final double centerX = offsetX + bigRadius * Math.sin(angle);
            final double centerY = offsetY - bigRadius * Math.cos(angle);
            ShapeWriter writer = new ShapeWriter(new PrintStream(os), centerX, centerY);
            Polygon.write(writer, 4, radius, i * Math.PI / 4);
        }
    }

    private static void writeOctagrams(OutputStream os) {
        final double length = 2 * radius / Math.sqrt(2);
        final double octaRadius = length / Math.sqrt(2 - Math.sqrt(2));
        final double bigRadius = octaRadius * (1 + Math.sqrt(2));
        for (int i = 0; i < 8; i++) {
            final double angle = Math.PI * i / 4 + Math.PI / 8;
            final double centerX = offsetX + bigRadius * Math.sin(angle);
            final double centerY = offsetY - bigRadius * Math.cos(angle);
            ShapeWriter writer = new ShapeWriter(new PrintStream(os), centerX, centerY);
            Polygon.write(writer, 8, octaRadius, Math.PI / 8);
        }
    }
}
