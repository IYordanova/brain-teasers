package tevalcourse.theory.symboltable.hw;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.math.BigDecimal;


public class PointSET {

    private final SET<Point2D> pointsSet;


    public PointSET() {
        pointsSet = new SET<>();
    }

    public boolean isEmpty() {
        return pointsSet.isEmpty();
    }

    public int size() {
        return pointsSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        pointsSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return pointsSet.contains(p);
    }

    public void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize();
        StdDraw.setScale();
        for (Point2D point : pointsSet) {
            StdDraw.filledCircle(point.x(), point.y(), 0.005);
        }
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> range = new SET<>();
        for (Point2D point : pointsSet) {
            if (rect.contains(point)) {
                range.add(point);
            }
        }
        return range;
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        Point2D nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point2D point : pointsSet) {
            if (point.equals(p)) {
                continue;
            }
            double distance = distance(p, point);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = point;
            }
        }
        return nearest;
    }

    private double distance(Point2D p1, Point2D p2) {
        double yDiff = Math.abs(BigDecimal.valueOf(p1.y())
                .subtract(BigDecimal.valueOf(p2.y()))
                .doubleValue());
        if (p1.x() == p2.x()) {
            return yDiff;
        }

        double xDiff = Math.abs(BigDecimal.valueOf(p1.x())
                .subtract(BigDecimal.valueOf(p2.x()))
                .doubleValue());
        if (p1.y() == p2.y()) {
            return xDiff;
        }

        return Math.sqrt(Math.pow(yDiff, 2) + Math.pow(xDiff, 2));
    }

    public static void main(String[] args) {
        PointSET points = new PointSET();

        assert points.isEmpty();

        points.insert(new Point2D(0.0, 0.0));
        points.insert(new Point2D(0.1, 0.4));
        points.insert(new Point2D(0.4, 0.3));
        points.insert(new Point2D(0.6, 0.5));
        points.insert(new Point2D(0.8, 0.6));

        assert !points.isEmpty();

        assert points.contains(new Point2D(0.1, 0.4));
        assert points.contains(new Point2D(0.0, 0.0));
        assert !points.contains(new Point2D(1, 2));

        assert points.distance(new Point2D(0.0, 0.0), new Point2D(0.4, 0.3)) == 0.5;
        assert points.distance(new Point2D(0.1, 0.4), new Point2D(0.1, 0.7)) == 0.3;
        assert points.distance(new Point2D(0.2, 0.3), new Point2D(0.4, 0.3)) == 0.2;

        Point2D nearest = points.nearest(new Point2D(0.4, 0.3));
        assert nearest.equals(new Point2D(0.6, 0.5));

        Iterable<Point2D> range = points.range(new RectHV(0.4, 0.3, 0.8, 0.6));
        range.forEach(System.out::println);

        points.draw();
    }

}