package tevalcourse.theory.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

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
        List<Point> added = new ArrayList<>();
        for (Point p : points) {
            Point[] copy = Arrays.copyOf(points, points.length);
            Arrays.sort(copy, p.slopeOrder());

            Point q = copy[length - 3];
            Point r = copy[length - 2];
            Point s = copy[length - 1];

            double pqSlope = p.slopeTo(q);
            double prSlope = p.slopeTo(r);
            double psSlope = p.slopeTo(s);

            if (pqSlope == prSlope && pqSlope == psSlope && prSlope == psSlope) {
                Point[] quadruple = {p, q, r, s};
                Arrays.sort(quadruple);
                Point start = quadruple[0];
                Point end = quadruple[3];
                if (!added.containsAll(List.of(start, end))) {
                    lineSegments.add(new LineSegment(start, end));
                    added.add(start);
                    added.add(end);
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
