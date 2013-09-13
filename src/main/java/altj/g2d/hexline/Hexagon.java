package altj.g2d.hexline;

import java.io.IOException;
import java.io.PrintStream;

public final class Hexagon {
    private static final String defaultStrokeColor = "#808080";
    private static final String defaultFillColor = "#FFFFFF";
    private static final String drawnStrokeColor = "#000000";
    private static final String drawnFillColor = "#808080";

    private static final double sin30 = 1d / 2;
    private static final double cos30 = Math.sqrt(3) / 2;
    private static final double scale = 0.9;
    private static final double[] unscaledVertexX = new double[] { 0, cos30, cos30, 0, -cos30, -cos30 };
    private static final double[] unscaledVertexY = new double[] { 1, sin30, -sin30, -1, -sin30, sin30 };

    private final double[] vertexX;
    private final double[] vertexY;
    private final double height;
    private final double width;
    private final double offsetX;
    private final double offsetY;

    public Hexagon(final double radius, final double offsetX, final double offsetY) {
        vertexX = new double[6];
        vertexY = new double[6];
        for (int i = 0; i < 6; i++) {
            vertexX[i] = scale * radius * unscaledVertexX[i];
            vertexY[i] = scale * radius * unscaledVertexY[i];
        }
        height = radius * 0.75;
        width = cos30 * radius;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void draw(PrintStream stream, Point center, boolean drawn) throws IOException {
        final StringBuilder shape = new StringBuilder("<polygon stroke=\"");
        if (drawn) {
            shape.append(drawnStrokeColor).append("\" fill=\"").append(drawnFillColor);
        } else {
            shape.append(defaultStrokeColor).append("\" fill=\"").append(defaultFillColor);
        }
        shape.append("\" points=\"");
        for (int i = 0; i < 6; i++) {
            final double x = offsetX + width * (center.y % 2 + 1) + 2 * width * center.x + vertexX[i];
            final double y = offsetY + height + 2 * height * center.y + vertexY[i];
            shape.append(String.format("%.4f,%.4f ", x, y));
        }
        shape.append("\"/>\n");
        stream.print(shape.toString());
    }
}
