package tevalcourse.theory.priorityqeueues.hw;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.List;

public class Board {
    private final int dimension;
    private final int[][] tiles;

    public Board(int[][] tiles) {
        this.dimension = tiles.length;
        this.tiles = Arrays.copyOf(tiles, dimension);
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        int tilesNotInPlace = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int number = tiles[i][j];
                if (number == 0) {
                    continue;
                }
                if (number != i * dimension + j + 1) {
                    tilesNotInPlace++;
                }
            }
        }
        return tilesNotInPlace;
    }

    public int manhattan() {
        int sumManhattan = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int number = tiles[i][j];
                if (number == 0) {
                    continue;
                }
                int remainder = number % dimension;
                int targetI = remainder == 0 ? number / dimension - 1 : number / dimension;
                int targetJ = remainder == 0 ? dimension - 1 : remainder - 1;
                int diff = Math.abs(targetI - i) + Math.abs(targetJ - j);
                sumManhattan += diff;
            }
        }
        return sumManhattan;
    }

    public boolean isGoal() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == dimension - 1 && j == dimension - 1) {
                    if (tiles[i][j] != 0) {
                        return false;
                    }
                } else if (i * dimension + j + 1 != tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int[][] n1 = new int[dimension][dimension];
        int[][] n2 = new int[dimension][dimension];
        int[][] n3 = new int[dimension][dimension];
        int[][] n4 = new int[dimension][dimension];

        int blankI = -1;
        int blankJ = -1;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] == 0) {
                    blankI = i;
                    blankJ = j;
                }
                n1[i][j] = tiles[i][j];
                n2[i][j] = tiles[i][j];
                n3[i][j] = tiles[i][j];
                n4[i][j] = tiles[i][j];
            }
        }

        if (blankI == 0 && blankJ == 0) {
            swapRight(n1, blankI, blankJ);
            swapBottom(n2, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2));
        }
        if (blankI == 0 && blankJ == dimension - 1) {
            swapLeft(n1, blankI, blankJ);
            swapBottom(n2, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2));
        }
        if (blankI == dimension - 1 && blankJ == dimension - 1) {
            swapLeft(n1, blankI, blankJ);
            swapTop(n2, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2));
        }
        if (blankI == dimension - 1 && blankJ == 0) {
            swapRight(n1, blankI, blankJ);
            swapTop(n2, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2));
        }

        if (blankI == 0) {
            swapLeft(n2, blankI, blankJ);
            swapRight(n1, blankI, blankJ);
            swapBottom(n3, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2), new Board(n3));
        }
        if (blankI == dimension - 1) {
            swapLeft(n2, blankI, blankJ);
            swapRight(n1, blankI, blankJ);
            swapTop(n3, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2), new Board(n3));
        }
        if (blankJ == 0) {
            swapRight(n1, blankI, blankJ);
            swapTop(n2, blankI, blankJ);
            swapBottom(n3, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2), new Board(n3));
        }
        if (blankJ == dimension - 1) {
            swapLeft(n1, blankI, blankJ);
            swapTop(n2, blankI, blankJ);
            swapBottom(n3, blankI, blankJ);
            return List.of(new Board(n1), new Board(n2), new Board(n3));
        }

        swapLeft(n1, blankI, blankJ);
        swapRight(n2, blankI, blankJ);
        swapTop(n3, blankI, blankJ);
        swapBottom(n4, blankI, blankJ);
        return List.of(new Board(n1), new Board(n2), new Board(n3), new Board(n4));
    }

    private void swap(int[][] a, int i, int j, int i2, int j2) {
        int swap = a[i][j];
        a[i][j] = a[i2][j2];
        a[i2][j2] = swap;
    }

    private void swapLeft(int[][] a, int i, int j) {
        swap(a, i, j, i, j - 1);
    }

    private void swapRight(int[][] a, int i, int j) {
        swap(a, i, j, i, j + 1);
    }

    private void swapTop(int[][] a, int i, int j) {
        swap(a, i, j, i - 1, j);
    }

    private void swapBottom(int[][] a, int i, int j) {
        swap(a, i, j, i + 1, j);
    }

    public Board twin() {
        int[][] copy = Arrays.copyOf(tiles, dimension);
        int i = StdRandom.uniform(dimension);
        int j = StdRandom.uniform(dimension);
        while (copy[i][j] == 0) {
            i = StdRandom.uniform(dimension);
            j = StdRandom.uniform(dimension);
        }
        int i2 = StdRandom.uniform(dimension);
        int j2 = StdRandom.uniform(dimension);
        while (copy[i2][j2] == 0) {
            i2 = StdRandom.uniform(dimension);
            j2 = StdRandom.uniform(dimension);
        }
        swap(copy, i, j, i2, j2);
        return new Board(copy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Board that = (Board) obj;
        if (that.dimension != this.dimension) {
            return false;
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(dimension);
        str.append("\n");
        for (int[] tile : tiles) {
            str.append(" ");
            for (int j = 0; j < dimension; j++) {
                str.append(tile[j]);
                if (j < dimension - 1) {
                    str.append(" ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        test2NeighbourInitialBoard();
        test3NeighbourInitialBoard();
        test4NeighbourInitialBoard();

        int[][] board3x3Goal = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        assert new Board(board3x3Goal).isGoal();
    }

    private static void test2NeighbourInitialBoard() {
        int[][] board3x3 = new int[][]{
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board = new Board(board3x3);

        int[][] board3x3NeighbourRight = {
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board neighbourRight = new Board(board3x3NeighbourRight);

        int[][] board3x3NeighbourBottom = {
                {4, 1, 3},
                {0, 2, 5},
                {7, 8, 6}
        };
        Board neighbourBottom = new Board(board3x3NeighbourBottom);

        assert board.dimension() == 3;
        assert board.hamming() == 4;
        assert board.manhattan() == 4;
        assert !board.isGoal();
        assert board.equals(new Board(board3x3));
        int neighboursCount = 0;
        for (Board neighbour : board.neighbors()) {
            assert neighbourRight.equals(neighbour) || neighbourBottom.equals(neighbour);
            neighboursCount++;
        }
        assert neighboursCount == 2;
        System.out.println(board.twin());
    }

    private static void test3NeighbourInitialBoard() {
        int[][] board3x3 = {
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Board board = new Board(board3x3);

        int[][] board3x3NeighbourRight = {
                {1, 3, 0},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board neighbourRight = new Board(board3x3NeighbourRight);

        int[][] board3x3NeighbourBottom = {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        };
        Board neighbourBottom = new Board(board3x3NeighbourBottom);

        int[][] board3x3NeighbourLeft = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board neighbourLeft = new Board(board3x3NeighbourLeft);

        assert board.dimension() == 3;
        assert board.hamming() == 3;
        assert board.manhattan() == 3;
        assert !board.isGoal();
        assert board.equals(new Board(board3x3));
        int neighboursCount = 0;
        for (Board neighbour : board.neighbors()) {
            assert neighbourRight.equals(neighbour)
                    || neighbourBottom.equals(neighbour)
                    || neighbourLeft.equals(neighbour);
            neighboursCount++;
        }
        assert neighboursCount == 3;
        System.out.println(board.twin());
    }

    private static void test4NeighbourInitialBoard() {
        int[][] board3x3 = {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        };

        Board board = new Board(board3x3);

        int[][] board3x3NeighbourRight = {
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6}
        };
        Board neighbourRight = new Board(board3x3NeighbourRight);

        int[][] board3x3NeighbourBottom = {
                {1, 2, 3},
                {4, 8, 5},
                {7, 0, 6}
        };
        Board neighbourBottom = new Board(board3x3NeighbourBottom);

        int[][] board3x3NeighbourLeft = {
                {1, 2, 3},
                {0, 4, 5},
                {7, 8, 6}
        };
        Board neighbourLeft = new Board(board3x3NeighbourLeft);

        int[][] board3x3NeighbourTop = {
                {1, 0, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board neighbourTop = new Board(board3x3NeighbourTop);

        assert board.dimension() == 3;
        assert board.hamming() == 2;
        assert board.manhattan() == 2;
        assert !board.isGoal();
        assert board.equals(new Board(board3x3));
        int neighboursCount = 0;
        for (Board neighbour : board.neighbors()) {
            assert neighbourRight.equals(neighbour)
                    || neighbourBottom.equals(neighbour)
                    || neighbourLeft.equals(neighbour)
                    || neighbourTop.equals(neighbour);
            neighboursCount++;
        }
        assert neighboursCount == 4;
        System.out.println(board.twin());
    }
}
