package altj.g2d.hexline;

import java.io.IOException;
import java.io.PrintStream;

public final class SamplePointWriter implements PointWriter, AutoCloseable {
    private final PrintStream stream;
    private final Hexagon hexagon;

    public SamplePointWriter(final PrintStream stream, final double pointRadius, final double offsetX,
            final double offsetY) {
        this.stream = stream;
        this.hexagon = new Hexagon(pointRadius, offsetX, offsetY);
    }

    public void init() throws IOException {
        StringBuilder header = new StringBuilder();
        header.append("<?xml version=\"1.0\" standalone=\"no\"?>\n")
                .append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n")
                .append("<svg width=\"20cm\" height=\"20cm\" viewBox=\"0 0 1000 1000\" xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n")
                .append("<desc>Line</desc>\n");
        stream.print(header.toString());
    }

    @Override
    public void write(Point point, boolean drawn) throws IOException {
        hexagon.draw(stream, point, drawn);
    }

    @Override
    public void close() throws IOException {
        stream.println("</svg>");
    }
}
