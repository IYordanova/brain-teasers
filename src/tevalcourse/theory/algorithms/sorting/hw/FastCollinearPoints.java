package tevalcourse.theory.algorithms.sorting.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FastCollinearPoints {

    private static final int POINTS_IN_LINE = 4;
    private final List<LineSegment> lineSegments = new ArrayList<>();


    public FastCollinearPoints(Point[] points) {
        findLineSegments(getValidatedInput(points));
    }

    private Point[] getValidatedInput(Point[] inputPoints) {
        if (inputPoints == null) {
            throw new IllegalArgumentException();
        }

        int inputLength = inputPoints.length;
        Point[] points = Arrays.copyOf(inputPoints, inputLength);
        for (int i = 0; i < inputLength; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < inputLength; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        return points;
    }

    private void findLineSegments(Point[] points) {
        int length = points.length;
        if (length < POINTS_IN_LINE) {
            return;
        }

        List<Map.Entry<Point, Point>> added = new ArrayList<>();
        for (int i = 0; i < points.length - 3; i ++) {
            Point p =  points[i];
            Point[] copy = Arrays.copyOf(points, points.length);
            Arrays.sort(copy, p.slopeOrder());

            for (int j = 0; j <= length - 3; j++) {
                if (checkSlope(added, p, copy[j], copy[j + 1], copy[j + 2])) {
                    break;
                }
            }
        }
    }

    private boolean checkSlope(List<Map.Entry<Point, Point>> added, Point p, Point q, Point r, Point s) {
        double pqSlope = p.slopeTo(q);
        double prSlope = p.slopeTo(r);
        double psSlope = p.slopeTo(s);

        if (pqSlope == prSlope && pqSlope == psSlope && prSlope == psSlope) {
            Point[] quadruple = {p, q, r, s};
            Arrays.sort(quadruple);
            Point start = quadruple[0];
            Point end = quadruple[3];
            if (!added.contains(Map.entry(start, end)) && !added.contains(Map.entry(end, start))) {
                lineSegments.add( new LineSegment(start, end));
                added.add(Map.entry(start, end));
                return true;
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
