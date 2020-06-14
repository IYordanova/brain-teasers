package tevalcourse.theory.priorityqeueues.hw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class Solver {

    private SearchNode solution;

    private static class SearchNode {
        private final Board board;
        private final int numOfMovesSoFar;
        private final SearchNode prev;
        final int manhattan;

        public SearchNode(Board board, int numOfMovesSoFar, SearchNode prev) {
            this.board = board;
            this.numOfMovesSoFar = numOfMovesSoFar;
            this.prev = prev;
            this.manhattan = board.manhattan();
        }
    }


    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        int dimension = initial.dimension();
        int maxRounds = perm(dimension * dimension - 1, 1);
        MinPQ<SearchNode> searchNodesQ = new MinPQ<>(Comparator.comparingInt(o -> o.manhattan));
        searchNodesQ.insert(new SearchNode(initial, 0, null));
        int moves = 0;
        while (!searchNodesQ.isEmpty() && moves < maxRounds) {
            SearchNode minNode = searchNodesQ.delMin();
            if (minNode.board.isGoal()) {
                solution = minNode;
                break;
            }
            moves++;
            for (Board neighbourBoard : minNode.board.neighbors()) {
                if (minNode.prev != null && minNode.prev.board.equals(neighbourBoard)) {
                    continue;
                }
                searchNodesQ.insert(new SearchNode(neighbourBoard, moves, minNode));
            }
        }
    }

    private int perm(int n, int acc) {
        if (n <= 1) {
            return acc;
        }
        return perm(n - 1, acc * n);
    }

    public boolean isSolvable() {
        return solution != null;
    }

    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return solution.numOfMovesSoFar;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        SearchNode node = solution;
        List<Board> boards = new LinkedList<>();
        while (node.prev != null) {
            boards.add(node.board);
            node = node.prev;
        }
        boards.add(node.board);
        Collections.reverse(boards);
        return boards;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Solver solver = new Solver(new Board(tiles));

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
