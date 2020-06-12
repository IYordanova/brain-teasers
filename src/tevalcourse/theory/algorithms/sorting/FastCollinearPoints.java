package tevalcourse.theory.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private static final int POINTS_IN_LINE = 4;
    private final List<LineSegment> lineSegments = new ArrayList<>();


    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        findLineSegments(Arrays.copyOf(points, points.length));
    }

    private void findLineSegments(Point[] points) {
        int length = points.length;
        for (int i = 0; i < length; i++) {
            Point p = points[i];
            Point[] copy = Arrays.copyOf(points, points.length);
            Arrays.sort(copy, p.slopeOrder());

            Point q, r, s;
            double pqSlope, prSlope, psSlope;
            if (i < length - 3) {
                q = copy[i + 1];
                r = copy[i + 2];
                s = copy[i + 3];

                pqSlope = p.slopeTo(q);
                prSlope = p.slopeTo(r);
                psSlope = p.slopeTo(s);

                if (pqSlope == prSlope && pqSlope == psSlope && prSlope == psSlope) {
                    Point[] quadruple = new Point[] {p, q, r, s};
                    Arrays.sort(quadruple);
                    lineSegments.add(new LineSegment(quadruple[0], quadruple[3]));
                    continue;
                }
            }
            if (i >= POINTS_IN_LINE) {
                q = copy[i - 1];
                r = copy[i - 2];
                s = copy[i - 3];

                pqSlope = p.slopeTo(q);
                prSlope = p.slopeTo(r);
                psSlope = p.slopeTo(s);

                if (pqSlope == prSlope && pqSlope == psSlope && prSlope == psSlope) {
                    Point[] quadruple = new Point[] {p, q, r, s};
                    Arrays.sort(quadruple);
                    lineSegments.add(new LineSegment(quadruple[0], quadruple[3]));
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
