package altj.g2d.hexline;

import java.util.Iterator;

public final class Line implements Shape {
    private final Point begin;

    private final int dx;
    private final int dy;
    private final int sx;
    private final int sy;
    private final boolean shifted;

    public Line(final Point begin, final Point end) {
        this.begin = begin;

        int dx = end.x - begin.x;
        int dy = end.y - begin.y;
        if (dx < 0) {
            dx = -dx;
            sx = -1;
        } else {
            sx = 1;
        }
        if (dy < 0) {
            dy = -dy;
            sy = -1;
        } else {
            sy = 1;
        }
        int cdx = dx + (dy + 1) / 2;
        if (cdx >= dy) {
            this.dx = cdx;
            shifted = true;
        } else {
            this.dx = dx;
            shifted = false;
        }
        this.dy = dy;
    }

    @Override
    public Iterator<Point> iterator() {
        return shifted ? new ShiftedLineIterator() : new LineIterator();
    }

    private class ShiftedLineIterator implements Iterator<Point> {
        private int x = 0;
        private int y = 0;
        private int error = 0;

        @Override
        public boolean hasNext() {
            return x <= dx;
        }

        @Override
        public Point next() {
            if (x > dx)
                return null;
            Point point = new Point(begin.x + (x - (y + 1) / 2) * sx, begin.y + y * sy);
            x++;
            if (x <= dx) {
                error += dy;
                if (2 * error >= dx) {
                    y++;
                    error -= dx;
                }
            }
            return point;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class LineIterator implements Iterator<Point> {
        private int x = 0;
        private int y = 0;
        private int error = 0;

        @Override
        public boolean hasNext() {
            return y <= dy;
        }

        @Override
        public Point next() {
            if (y > dy)
                return null;
            Point point = new Point(begin.x + x * sx, begin.y + y * sy);
            y++;
            if (y <= dy) {
                error = error + dx;
                if ((2 * error >= dy) && ((y & 1) == 0)) {
                    error -= dy;
                    x++;
                }
            }
            return point;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
