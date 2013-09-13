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
        private Point point = null;
        private int d = 0;
        private int rest;

        private ShiftedLineIterator() {
            rest = dx;
        }

        @Override
        public boolean hasNext() {
            return (point == null) || (rest != 0);
        }

        @Override
        public Point next() {
            if (point == null) {
                point = begin;
                return point;
            }
            if (rest == 0)
                return null;
            rest--;
            d = d + dy;
            if (2 * d < dx) {
                point = new Point(point.x + 1, point.y);
            } else {
                d -= dx;
                point = new Point(point.x + 1, point.y + 1);
            }
            return new Point(begin.x + (point.x - (point.y + 1) / 2) * sx, begin.y + point.y * sy);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class LineIterator implements Iterator<Point> {
        private Point point = null;
        private int d = 0;
        private int rest;

        private LineIterator() {
            rest = dy;
        }

        @Override
        public boolean hasNext() {
            return (point == null) || (rest != 0);
        }

        @Override
        public Point next() {
            if (point == null) {
                point = begin;
                return point;
            }
            if (rest == 0)
                return null;
            rest--;
            d = d + dx;
            if (2 * d < dy) {
                point = new Point(point.x, point.y + 1);
            } else {
                d -= dy;
                point = new Point(point.x + 1, point.y + 1);
            }
            return new Point(begin.x + point.x * sx, begin.y + point.y * sy);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
