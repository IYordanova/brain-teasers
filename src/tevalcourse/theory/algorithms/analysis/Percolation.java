package tevalcourse.theory.algorithms.analysis;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;


/*
        -1 - closed/ blocked
         0 - opened
         N - root parent
 */

public class Percolation {
    private final int n;
    public final int[][] grid;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Cannot create grid with size less than or equal to 0");
        }
        this.n = n;
        this.grid = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            grid[i] = new int[n+1];
            for (int j = 1; j <= n; j++) {
                grid[i][j] = -1;
            }
        }
    }

    public void open(int row, int col) {
        validatePosition(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = (row == 1 ? col : 0);
        }
    }

    public boolean isOpen(int row, int col) {
        validatePosition(row, col);
        return grid[row][col] > -1;
    }

    public boolean isFull(int row, int col) {
        validatePosition(row, col);
        return grid[row][col] >= 1;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isOpen(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                return true;
            }
        }
        return false;
    }

    private void validatePosition(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException(String.format("Invalid position (%s,%s)", row, col));
        }
    }


    public static void main(String[] args) {
        int n = 20;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
            for (int i = 1; i <= n; i++) {
                System.out.println(Arrays.toString(percolation.grid[i]));
            }
            System.out.println();
        }
        System.out.println(percolation.numberOfOpenSites());
    }

}