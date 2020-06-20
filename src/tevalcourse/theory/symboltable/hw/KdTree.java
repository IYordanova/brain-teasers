package tevalcourse.theory.symboltable.hw;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.math.BigDecimal;

public class KdTree {

    static class Node {
        private Point2D point;
        private Node left;
        private Node right;
        private boolean red;

        public Node(Point2D point, boolean red) {
            this.point = point;
            this.red = red;
        }

        public Double getKey() {
            return red ? point.x() : point.y();
        }
    }

    private Node root;
    private int size;


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, p, true);
        size++;
    }

    private Node insert(Node node, Point2D point, boolean red) {
        if (node == null) {
            return new Node(point, red);
        }
        int cmp = getKey(point, red).compareTo(node.getKey());
        if (cmp < 0) {
            node.left = insert(node.left, point, !red);
        } else if (cmp > 0) {
            node.right = insert(node.right, point, !red);
        } else {
            node.point = point;
        }
        return node;
    }

    private Double getKey(Point2D point, boolean red) {
        return red ? point.x() : point.y();
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return find(p) == null;
    }

    private Node find(Point2D point) {
        Node node = root;
        while (node != null) {
            int cmp = getKey(point, node.red).compareTo(node.getKey());
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize();
        StdDraw.setScale();
        draw(root);
        StdDraw.show();
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        draw(node.left);

        StdDraw.filledCircle(node.point.x(), node.point.y(), 0.005);
        if (node.red) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.point.x(), Double.MIN_VALUE, node.point.x(), Double.MAX_VALUE);
        } else {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(Double.MIN_VALUE, node.point.y(), Double.MAX_VALUE, node.point.y());
        }

        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> range = new SET<>();
        addToRange(root, rect, range);
        return range;
    }

    private void addToRange(Node node, RectHV rect, SET<Point2D> range) {
        if (node == null) {
            return;
        }

        RectHV leftNodeRect, rightNodeRect;
        if (node.red) {
            leftNodeRect = new RectHV(Double.MIN_VALUE, Double.MIN_VALUE, node.point.x(), Double.MAX_VALUE);
            rightNodeRect = new RectHV(node.point.x(), Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            leftNodeRect = new RectHV(Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, node.point.y());
            rightNodeRect = new RectHV(Double.MIN_VALUE, node.point.y(), Double.MAX_VALUE, Double.MAX_VALUE);
        }

        if (rect.intersects(leftNodeRect)) {
            node = node.left;
            addToRange(node, rect, range);
        }
        if (rect.intersects(rightNodeRect)) {
            node = node.right;
            addToRange(node, rect, range);
        }

        if (rect.contains(node.point)) {
            range.add(node.point);
        }
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return nearest(p, root, null, Double.MAX_VALUE);
    }

    private Point2D nearest(Point2D point, Node node, Point2D nearest, double minDistance) {
        if (node == null) {
            return nearest;
        }

        RectHV leftNodeRect, rightNodeRect;
        if (node.red) {
            leftNodeRect = new RectHV(Double.MIN_VALUE, Double.MIN_VALUE, node.point.x(), Double.MAX_VALUE);
            rightNodeRect = new RectHV(node.point.x(), Double.MIN_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            leftNodeRect = new RectHV(Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE, node.point.y());
            rightNodeRect = new RectHV(Double.MIN_VALUE, node.point.y(), Double.MAX_VALUE, Double.MAX_VALUE);
        }

        double leftDistance = distance(point, leftNodeRect);
        if (minDistance > leftDistance) {
            node = node.left;
            return nearest(point, node, node != null ? node.point : nearest, leftDistance);
        }

        double rightDistance = distance(point, rightNodeRect);
        if (minDistance > rightDistance) {
            node = node.right;
            return nearest(point, node, node != null ? node.point : nearest, rightDistance);
        }

        return node.point;
    }

    private double distance(Point2D p1, RectHV rect) {
        double yDiff = Math.abs(BigDecimal.valueOf(p1.y())
                .subtract(BigDecimal.valueOf(rect.ymax()))
                .doubleValue());
        if (p1.x() == rect.xmax()) {
            return yDiff;
        }

        double xDiff = Math.abs(BigDecimal.valueOf(p1.x())
                .subtract(BigDecimal.valueOf(rect.xmax()))
                .doubleValue());
        if (p1.y() == rect.ymax()) {
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

        Point2D nearest = points.nearest(new Point2D(0.4, 0.3));
        assert nearest.equals(new Point2D(0.6, 0.5));

        Iterable<Point2D> range = points.range(new RectHV(0.4, 0.3, 0.8, 0.6));
        range.forEach(System.out::println);

        points.draw();
    }
}
