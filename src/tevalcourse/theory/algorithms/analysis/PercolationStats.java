package tevalcourse.theory.algorithms.analysis;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final double[] times;

    public PercolationStats(int gridSize, int testRuns) {
        times = new double[testRuns];
        Percolation percolation = new Percolation(gridSize);
        for (int t = 0; t < testRuns; t++) {
            Stopwatch stopwatch = new Stopwatch();
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, gridSize + 1);
                int col = StdRandom.uniform(1, gridSize + 1);
                percolation.open(row, col);
            }
            times[t] = stopwatch.elapsedTime();
            // PercolationVisualizer.draw(percolation, gridSize);
        }
    }

    public double confidenceHi() {
        double mean = mean();
        double stdDev = stddev();
        return mean + CONFIDENCE_95 * stdDev;
    }

    public double confidenceLo() {
        double mean = mean();
        double stdDev = stddev();
        return mean - CONFIDENCE_95 * stdDev;
    }

    public double mean() {
        return StdStats.mean(times);
    }

    public double stddev() {
        return StdStats.stddev(times);
    }


    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Expected program arguments n and T");
        }

        int gridSize = Integer.parseInt(args[0]);
        int testRuns = Integer.parseInt(args[1]);

        if (gridSize <= 0 || testRuns <= 0) {
            throw new IllegalArgumentException("n and T should be positive numbers");
        }

        PercolationStats stats = new PercolationStats(gridSize, testRuns);
        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(String.format("[%s, %s]", stats.confidenceLo(), stats.confidenceHi()));
    }
}
