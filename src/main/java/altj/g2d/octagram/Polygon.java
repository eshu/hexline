package altj.g2d.octagram;

public class Polygon {
    private static final String stroke = "#000000";

    public static void write(final ShapeWriter writer, final int n, final double radius, final double rotate) {
        Point[] polygon = new Point[n];
        for (int i = 0; i < n; i++) {
            final double angle = 2 * i * Math.PI / n + rotate;
            final double x = Math.sin(angle);
            final double y = Math.cos(angle);
            polygon[i] = new Point(x * radius, y * radius);
        }
        writer.write(polygon, stroke);
    }
}
