package tevalcourse.theory.symboltable.hw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private static final RectHV WRAPPER_RECT = new RectHV(0, 0, 1, 1);

    private static class Node {
        private final Point2D point;
        private final boolean red;
        private Node left;
        private Node right;

        public Node(Point2D point, boolean red) {
            this.point = point;
            this.red = red;
        }

        public double getKey() {
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
    }

    private Node insert(Node node, Point2D point, boolean red) {
        if (node == null) {
            size++;
            return new Node(point, red);
        }

        if (node.point.x() == point.x() && node.point.y() == point.y()) {
            return node;
        }

        if (node.red && point.x() < node.point.x() || !node.red && point.y() < node.point.y()) {
            node.left = insert(node.left, point, !node.red);
        } else {
            node.right = insert(node.right, point, !node.red);
        }
        return node;
    }

    private double getKey(Point2D point, boolean red) {
        return red ? point.x() : point.y();
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D point) {
        if (node == null) {
            return false;
        }
        if (node.point.x() == point.x() && node.point.y() == point.y()) {
            return true;
        }

        if (node.red && point.x() < node.point.x() || !node.red && point.y() < node.point.y()) {
            return contains(node.left, point);
        } else {
            return contains(node.right, point);
        }
    }

    public void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setCanvasSize();
        StdDraw.setScale();
        draw(root, WRAPPER_RECT);
        StdDraw.show();
    }

    private void draw(Node node, RectHV rect) {
        if (node == null) {
            return;
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        new Point2D(node.point.x(), node.point.y()).draw();

        Point2D min, max;
        if (node.red) {
            StdDraw.setPenColor(StdDraw.RED);
            min = new Point2D(node.point.x(), rect.ymin());
            max = new Point2D(node.point.x(), rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            min = new Point2D(rect.xmin(), node.point.y());
            max = new Point2D(rect.xmax(), node.point.y());
        }

        StdDraw.setPenRadius();
        min.drawTo(max);

        draw(node.left, leftRect(rect, node));
        draw(node.right, rightRect(rect, node));
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> range = new ArrayList<>();
        range(root, WRAPPER_RECT, rect, range);
        return range;
    }

    private void range(Node node, RectHV rect, RectHV searchRect, List<Point2D> range) {
        if (node == null) {
            return;
        }

        if (searchRect.intersects(rect)) {
            if (searchRect.contains(node.point)) {
                range.add(node.point);
            }
            range(node.left, leftRect(rect, node), searchRect, range);
            range(node.right, rightRect(rect, node), searchRect, range);
        }
    }

    private RectHV leftRect(final RectHV rect, final Node node) {
        if (node.red) {
            return new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
        }
    }


    private RectHV rightRect(final RectHV rect, final Node node) {
        if (node.red) {
            return new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            return new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
        }
    }


    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return nearest(root, WRAPPER_RECT, p, null);
    }

    private Point2D nearest(Node node, RectHV rect, Point2D query, Point2D candidate) {
        if (node == null) {
            return candidate;
        }

        double dqn = 0.0;
        double drq = 0.0;

        Point2D nearest = candidate;

        if (nearest != null) {
            dqn = query.distanceSquaredTo(nearest);
            drq = rect.distanceSquaredTo(query);
        }

        if (nearest == null || dqn > drq) {
            if (nearest == null || dqn > query.distanceSquaredTo(node.point)) {
                nearest = node.point;
            }
            if (node.red) {
                RectHV left = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
                RectHV right = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());

                if (query.x() < node.point.x()) {
                    nearest = nearest(node.left, left, query, nearest);
                    nearest = nearest(node.right, right, query, nearest);
                } else {
                    nearest = nearest(node.right, right, query, nearest);
                    nearest = nearest(node.left, left, query, nearest);
                }
            } else {
                RectHV left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
                RectHV right = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());

                if (query.y() < node.point.y()) {
                    nearest = nearest(node.left, left, query, nearest);
                    nearest = nearest(node.right, right, query, nearest);
                } else {
                    nearest = nearest(node.right, right, query, nearest);
                    nearest = nearest(node.left, left, query, nearest);
                }
            }
        }

        return nearest;
    }


    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        KdTree points = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            points.insert(p);
        }

        points.range(new RectHV(0.0, 0.5, 0.0, 0.75))
            .forEach(System.out::println);
    }
}
