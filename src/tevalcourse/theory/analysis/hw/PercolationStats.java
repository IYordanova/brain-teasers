package tevalcourse.theory.analysis.hw;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private final int testRuns;
    private final double[] fractionOfOpenSites;

    public PercolationStats(int n, int testRuns) {
        if (n <= 0 || testRuns <= 0) {
            throw new IllegalArgumentException("n and T should be positive numbers");
        }
        this.testRuns = testRuns;
        this.fractionOfOpenSites = new double[testRuns];
        double numOfSites = n * n;

        for (int t = 0; t < testRuns; t++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            fractionOfOpenSites[t] = percolation.numberOfOpenSites() / numOfSites;
        }
    }

    public double confidenceHi() {
        double mean = mean();
        double stdDev = stddev();
        return mean + CONFIDENCE_95 * stdDev / Math.sqrt(testRuns);
    }

    public double confidenceLo() {
        double mean = mean();
        double stdDev = stddev();
        return mean - CONFIDENCE_95 * stdDev / Math.sqrt(testRuns);
    }

    public double mean() {
        return StdStats.mean(fractionOfOpenSites);
    }

    public double stddev() {
        return StdStats.stddev(fractionOfOpenSites);
    }


    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Expected program arguments n and T");
        }

        int gridSize = Integer.parseInt(args[0]);
        int testRuns = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(gridSize, testRuns);
        System.out.println(String.format("mean = %s", stats.mean()));
        System.out.println(String.format("stddev = %s", stats.stddev()));
        System.out.println(String.format("95%% confidence interval = [%s, %s]", stats.confidenceLo(), stats.confidenceHi()));
    }
}
