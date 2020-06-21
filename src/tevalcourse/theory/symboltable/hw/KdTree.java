package tevalcourse.theory.symboltable.hw;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private static final double MIN_VALUE = 0.0;
    private static final double MAX_VALUE = 1.0;
    private static final RectHV WRAPPER_RECT = new RectHV(MIN_VALUE, MIN_VALUE, MAX_VALUE, MAX_VALUE);

    private static class Node {
        private Point2D point;
        private Node left;
        private Node right;
        private final boolean red;

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
        size++;
    }

    private Node insert(Node node, Point2D point, boolean red) {
        if (node == null) {
            return new Node(point, red);
        }
        double cmp = getKey(point, red) - node.getKey();
        if (cmp < 0) {
            node.left = insert(node.left, point, !red);
        } else if (cmp > 0) {
            node.right = insert(node.right, point, !red);
        } else {
            node.point = point;
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
        return find(p) != null;
    }

    private Node find(Point2D point) {
        Node node = root;
        while (node != null) {
            double cmp = getKey(point, node.red) - node.getKey();
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
        if (node == null) return;

        if (searchRect.intersects(rect)) {
            final Point2D p = new Point2D(node.point.x(), node.point.y());
            if (searchRect.contains(p)) range.add(p);
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
            if (nearest == null || dqn > query.distanceSquaredTo(node.point) && !node.point.equals(query)) {
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
        KdTree points = new KdTree();

        assert points.isEmpty();

        Point2D p = new Point2D(0.4, 0.3);
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(0.1, 0.4);
        points.insert(p1);
        points.insert(p2);
        points.insert(p);
        points.insert(new Point2D(0.6, 0.5));
        points.insert(new Point2D(0.8, 0.6));

        assert !points.isEmpty();

        assert points.contains(new Point2D(0.1, 0.4));
        assert points.contains(new Point2D(0.0, 0.0));
        assert !points.contains(new Point2D(1, 2));

        Point2D nearest = points.nearest(p);
        System.out.println(String.format("Nearest to %s is %s ", p, nearest));

        nearest = points.nearest(p1);
        System.out.println(String.format("Nearest to %s is %s ", p1, nearest));

        nearest = points.nearest(p2);
        System.out.println(String.format("Nearest to %s is %s ", p2, nearest));

        Iterable<Point2D> range = points.range(new RectHV(0.4, 0.3, 0.8, 0.6));
        range.forEach(System.out::println);

        points.draw();
    }
}
