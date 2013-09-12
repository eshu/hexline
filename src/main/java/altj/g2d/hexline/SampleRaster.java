package altj.g2d.hexline;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SampleRaster implements Raster {
    private final int width;
    private final int height;
    private final Set<Point> points;

    public SampleRaster(final int width, final int height) {
        this.width = width;
        this.height = height;
        points = new HashSet<>();
    }

    @Override
    public void draw(final Shape shape) {
        for (Point point : shape) {
            if ((point.x >= 0) && (point.y >= 0) && (point.x < width) && (point.y < height)) {
                points.add(point);
            }
        }
    }

    public void write(final PointWriter writer) throws IOException {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Point point = new Point(x, y);
                writer.write(point, points.contains(point));
            }
        }
    }
}
