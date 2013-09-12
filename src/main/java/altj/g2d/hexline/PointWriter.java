package altj.g2d.hexline;

import java.io.IOException;

public interface PointWriter {
    void write(final Point point, final boolean drawn) throws IOException;
}
