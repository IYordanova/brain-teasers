package tevalcourse.theory.tries.hw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {

    private final DictionaryTrie dictionaryTrie = new DictionaryTrie();

    public BoggleSolver(String[] dictionary) {
        for (String word : dictionary) {
            if (word.length() > 2) {
                dictionaryTrie.put(word, word);
            }
        }
    }

    public Iterable<String> getAllValidWords(BoggleBoard board) {
        BoardGraph boardGraph = new BoardGraph(board);
        return boardGraph.searchValidWords(dictionaryTrie);
    }

    public int scoreOf(String word) {
        if (!dictionaryTrie.contains(word)) {
            return 0;
        }
        return getScoreOf(word);
    }

    private int getScoreOf(String word) {
        int len = word.length();
        if (len < 3) {
            return 0;
        }
        if (len == 3 || len == 4) {
            return 1;
        }
        if (len == 5) {
            return 2;
        }
        if (len == 6) {
            return 3;
        }
        if (len == 7) {
            return 5;
        }
        return 11;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
