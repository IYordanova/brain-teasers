package tevalcourse.theory.algorithms.analysis;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;
    private final boolean[] opened;
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Cannot create grid with size less than or equal to 0");
        }
        this.n = n;
        this.opened = new boolean[n * n + 1];
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 1);
    }

    private int index(int row, int col) {
        return (row - 1) * n + col;
    }

    public void open(int row, int col) {
        validatePosition(row, col);
        if (!isOpen(row, col)) {
            int index = index(row, col);
            opened[index] = true;
            int topRow = row - 1;
            if (topRow >= 1 && isOpen(topRow, col)) {
                weightedQuickUnionUF.union(index, index - n);
            }
            int rightCol = col + 1;
            if (rightCol <= n && isOpen(row, rightCol)) {
                weightedQuickUnionUF.union(index, index + 1);
            }
            int bottomRow = row + 1;
            if (bottomRow <= n && isOpen(bottomRow, col)) {
                weightedQuickUnionUF.union(index, index + n);
            }
            int leftCol = col - 1;
            if (leftCol >= 1 && isOpen(row, leftCol)) {
                weightedQuickUnionUF.union(index, index - 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validatePosition(row, col);
        return opened[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validatePosition(row, col);
        int index = index(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int childVal = weightedQuickUnionUF.find(index);
        for (int i = 1; i <= n; i++) {
            int parentVal = weightedQuickUnionUF.find(i);
            if (parentVal == childVal) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : opened) {
            if (b) {
                count++;
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
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException(String.format("Invalid position (%s,%s)", row, col));
        }
    }
}