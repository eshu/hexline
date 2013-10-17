package altj.g2d.polygram;

import java.io.IOException;
import java.io.PrintStream;

public final class ShapeWriter {
    private final PrintStream stream;
    private final double offsetX;
    private final double offsetY;

    public ShapeWriter(final PrintStream stream, final double offsetX, final double offsetY) {
        this.stream = stream;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void init() throws IOException {
        StringBuilder header = new StringBuilder();
        header.append("<?xml version=\"1.0\" standalone=\"no\"?>\n")
                .append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n")
                .append("<svg width=\"40cm\" height=\"40cm\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")
                .append("<desc>Polygram</desc>\n");
        stream.print(header.toString());
    }

    public void write(final Point[] points, final String stroke) {
        write(points, stroke, "#FFFFFF");
    }

    public void write(final Point[] points, final String stroke, final String fill) {
        final StringBuilder shape = new StringBuilder("<polygon stroke=\"");
        shape.append(stroke).append("\" fill=\"").append(fill).append("\" points=\"");
        for (Point point : points) {
            shape.append(String.format("%.4f,%.4f ", offsetX + point.x, offsetY - point.y));
        }
        shape.append("\"/>\n");
        stream.print(shape.toString());
    }

    public void write(final Line line, final String stroke) {
        stream.print(String.format("<line stroke=\"%s\" x1=\"%.4f\" y1=\"%.4f\" x2=\"%.4f\" y2=\"%.4f\"/>", stroke,
                offsetX + line.begin.x, offsetY - line.begin.y, offsetX + line.end.x, offsetY - line.end.y));
    }

    public void write(final String text, final double x, final double y) {
        stream.print(String.format(
                "<text x=\"%.4f\" y=\"%.4f\" text-anchor=\"middle\" dominant-baseline=\"middle\">%s</text>", offsetX
                        + x, offsetY - y, text));
    }

    public void close() throws IOException {
        stream.println("</svg>");
    }
}
