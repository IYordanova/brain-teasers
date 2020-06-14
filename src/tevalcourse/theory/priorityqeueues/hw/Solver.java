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

        public SearchNode(Board board, int numOfMovesSoFar, SearchNode prev) {
            this.board = board;
            this.numOfMovesSoFar = numOfMovesSoFar;
            this.prev = prev;
        }
    }


    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        Board twin = initial.twin();

        MinPQ<SearchNode> initialPq = new MinPQ<>(Comparator.comparingInt(o -> o.board.manhattan() + o.numOfMovesSoFar));
        initialPq.insert(new SearchNode(initial, 0, null));

        MinPQ<SearchNode> twinPq = new MinPQ<>(Comparator.comparingInt(o -> o.board.manhattan() + o.numOfMovesSoFar));
        twinPq.insert(new SearchNode(twin, 0, null));

        while (!initialPq.isEmpty() && !twinPq.isEmpty()) {
            SearchNode initialMinNode = initialPq.delMin();
            if (initialMinNode.board.isGoal()) {
                solution = initialMinNode;
                break;
            }
            for (Board neighbourBoard : initialMinNode.board.neighbors()) {
                if (initialMinNode.prev != null && initialMinNode.prev.board.equals(neighbourBoard)) {
                    continue;
                }
                initialPq.insert(new SearchNode(neighbourBoard, initialMinNode.numOfMovesSoFar + 1, initialMinNode));
            }

            SearchNode twinMinNode = twinPq.delMin();
            if (twinMinNode.board.isGoal()) {
                solution = null; // unsolvable
                break;
            }
            for (Board neighbourBoard : twinMinNode.board.neighbors()) {
                if (twinMinNode.prev != null && twinMinNode.prev.board.equals(neighbourBoard)) {
                    continue;
                }
                twinPq.insert(new SearchNode(neighbourBoard, initialMinNode.numOfMovesSoFar + 1, twinMinNode));
            }
        }
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
