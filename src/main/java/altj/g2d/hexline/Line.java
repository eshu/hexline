package altj.g2d.hexline;

import java.util.Iterator;

public final class Line implements Shape {
    private final Point begin;

    private final int dx;
    private final int dy;
    private final int sx;
    private final int sy;

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
        this.dx = dx + dy;
        this.dy = dy;
    }

    @Override
    public Iterator<Point> iterator() {
        return new LineIterator();
    }

    private class LineIterator implements Iterator<Point> {
        private Point point = null;
        private int d = 0;
        private int rest;

        private LineIterator() {
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
            if (d > dx) {
                d %= dx;
                point = new Point(point.x + 1, point.y + 1);
            } else {
                point = new Point(point.x + 1, point.y);
            }
            return new Point(begin.x + (point.x - point.y) * sx, begin.y + point.y * sy);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
