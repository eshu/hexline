package altj.g2d.hexline;

public final class Point {
    public final int x;
    public final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if ((o != null) && (o instanceof Point)) {
            final Point point = (Point) o;
            return (x == point.x) && (y == point.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 3 + y * 7;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ')';
    }
}
