package tevalcourse.theory.symboltable.hw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;


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
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        SET<Point2D> range = new SET<>();
        for (Point2D point : pointsSet) {
            if (rect.contains(point)) {
                range.add(point);
            }
        }
        return range;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        Point2D nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point2D point : pointsSet) {
            double distance = p.distanceSquaredTo(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = point;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET points = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            points.insert(p);
        }

        System.out.println(points.nearest(new Point2D(0.75, 0.625)));
    }

}