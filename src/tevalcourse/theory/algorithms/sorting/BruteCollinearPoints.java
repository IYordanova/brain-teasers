package tevalcourse.theory.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private static final int POINTS_IN_LINE = 4;
    private final List<LineSegment> lineSegments = new ArrayList<>();


    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        findLineSegments(Arrays.copyOf(points, points.length));
    }

    private void findLineSegments(Point[] points) {
        int length = points.length;
        Arrays.sort(points);
        combinationUtil(points, new Point[POINTS_IN_LINE], 0, length - 1, 0);
    }

    private void combinationUtil(Point[] allPoints, Point[] quadruple, int start, int end, int index) {
        if (index == POINTS_IN_LINE) {
            double pqSlope = quadruple[0].slopeTo(quadruple[1]);
            double prSlope = quadruple[0].slopeTo(quadruple[2]);
            double psSlope = quadruple[0].slopeTo(quadruple[3]);

            if (pqSlope == prSlope && pqSlope == psSlope && prSlope == psSlope) {
                Arrays.sort(quadruple);
                LineSegment lineSegment = new LineSegment(quadruple[0], quadruple[3]);
                lineSegments.add(lineSegment);
            }
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= POINTS_IN_LINE - index; i++) {
            quadruple[index] = allPoints[i];
            combinationUtil(allPoints, quadruple, i + 1, end, index + 1);
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
