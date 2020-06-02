package tevalcourse.theory.algorithms.analysis;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

public class PercolationStats {

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Expected program arguments n and T");
        }

        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException("n and T should be positive numbers");
        }

        ArrayList<Double> times = new ArrayList<>();
        Percolation percolation = new Percolation(n);
        for(int t = 0; t < T; t ++) {
            Stopwatch stopwatch = new Stopwatch();
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            times.add(stopwatch.elapsedTime());
            //PercolationVisualizer.draw(percolation, n);
        }

        double[] tms = times.stream().mapToDouble(i -> i).toArray();
        System.out.println(StdStats.mean(tms));
        System.out.println(StdStats.stddev(tms));
        System.out.println(StdStats.stddev(tms, -1, 1));
    }
}
