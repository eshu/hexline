package altj.g2d.polygram;

public final class Polygram {
    private static final String polygonStroke = "#808080";
    private static final String polygramStroke = "#000000";

    private static final String[] colorStrokes = new String[]{"#800000", "#008000", "#000080"};

    public static void write(final ShapeWriter writer, final int n, final int k, final double radius, final double textRadius) {
        Point[] polygon = new Point[n];
        for (int i = 0; i < n; i++) {
            final double angle = 2 * i * Math.PI / n;
            final double x = Math.sin(angle);
            final double y = Math.cos(angle);
            polygon[i] = new Point(x * radius, y * radius);
            writer.write(Integer.toString(i), x * textRadius, y * textRadius);
        }
        if (k > 1) {
            writer.write(polygon, polygonStroke);
        } else {
            writer.write(polygon, polygramStroke);
            return;
        }
        for (int i = 0; i < n; i++) {
            final Line line = new Line(polygon[i], polygon[(i + k) % n]);
            writer.write(line, polygramStroke);
        }
    }

    public static void write(final ShapeWriter writer, final int n, final int k, final double radius, final double textRadius, final boolean fill) {
        Point[] polygram = new Point[n];
        for (int i = 0; i < n; i++) {
            final double angle = 2 * ((i * k) % n) * Math.PI / n;
            final double x = Math.sin(angle);
            final double y = Math.cos(angle);
            polygram[i] = new Point(x * radius, y * radius);
            writer.write(Integer.toString(i), x * textRadius, y * textRadius);
        }
        writer.write(polygram, polygramStroke, fill ? polygramStroke : polygonStroke);
    }

    public static void write(final ShapeWriter writer, final int n, final int k, final double radius, final double textRadius, final int colors) {
        Point[] polygon = new Point[n];
        for (int i = 0; i < n; i++) {
            final double angle = 2 * i * Math.PI / n;
            final double x = Math.sin(angle);
            final double y = Math.cos(angle);
            polygon[i] = new Point(x * radius, y * radius);
            writer.write(Integer.toString(i), x * textRadius, y * textRadius);
        }
        if (k > 1) {
            writer.write(polygon, polygonStroke);
        } else {
            writer.write(polygon, polygramStroke);
            return;
        }
        for (int i = 0; i < n; i++) {
            final Line line = new Line(polygon[i], polygon[(i + k) % n]);
            writer.write(line, colorStrokes[i % colors]);
        }
    }
}
