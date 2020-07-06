package tevalcourse.theory.tries.hw;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class BoardGraph {

    private final BoggleBoard board;
    private final Bag<Integer>[] adj;
    private final int rows;
    private final int cols;

    private boolean[] marked;
    private Stack<Integer> visitingDices;

    public BoardGraph(BoggleBoard board) {
        this.board = board;
        this.rows = board.rows();
        this.cols = board.cols();
        this.adj = buildBoardGraph(rows, cols);
    }

    private Bag<Integer>[] buildBoardGraph(int rows, int cols) {
        Bag<Integer>[] adj = (Bag<Integer>[]) new Bag[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int v = i * cols + j;
                adj[v] = new Bag<>();
                if (inRange(i - 1, j, rows, cols)) {
                    adj[v].add((i - 1) * cols + j);
                }
                if (inRange(i + 1, j, rows, cols)) {
                    adj[v].add((i + 1) * cols + j);
                }
                if (inRange(i, j + 1, rows, cols)) {
                    adj[v].add(i * cols + j + 1);
                }
                if (inRange(i, j - 1, rows, cols)) {
                    adj[v].add(i * cols + j - 1);
                }
                if (inRange(i + 1, j - 1, rows, cols)) {
                    adj[v].add((i + 1) * cols + j - 1);
                }
                if (inRange(i + 1, j + 1, rows, cols)) {
                    adj[v].add((i + 1) * cols + j + 1);
                }
                if (inRange(i - 1, j - 1, rows, cols)) {
                    adj[v].add((i - 1) * cols + j - 1);
                }
                if (inRange(i - 1, j + 1, rows, cols)) {
                    adj[v].add((i - 1) * cols + j + 1);
                }
            }
        }
        return adj;
    }

    private boolean inRange(int i, int j, int rows, int cols) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public Set<String> searchValidWords(DictionaryTrie trie) {
        Set<String> words = new HashSet<>();
        int numVertices = cols * rows;
        for (int v = 0; v < numVertices; v++) {
            visitingDices = new Stack<>();
            marked = new boolean[rows * cols];
            visitingDices.push(v);
            marked[v] = true;

            int i = v / cols;
            int j = v % cols;
            char letterOnBoard = board.getLetter(i, j);

            if (letterOnBoard == 'Q') {
                searchValidWords(v, trie.getRoot().next['Q' - 'A'].next['U' - 'A'], "QU", words);
            } else {
                searchValidWords(v, trie.getRoot().next[letterOnBoard - 'A'], letterOnBoard + "", words);
            }
        }
        return words;
    }


    private void searchValidWords(int v, DictionaryTrie.Node x, String prefix, Set<String> words) {
        if (prefix.length() > 2 && x != null && x.word != null && x.word.equals(prefix)) {
            words.add(prefix);
        }
        for (int w : adj[v]) {
            int i = w / cols;
            int j = w % cols;
            char letterOnBoard = board.getLetter(i, j);
            if (!marked[w] && x != null && x.next[letterOnBoard - 'A'] != null) {
                visitingDices.push(w);
                marked[w] = true;
                if (letterOnBoard == 'Q') {
                    searchValidWords(w, x.next['Q' - 'A'].next['U' - 'A'], prefix + "QU", words);
                } else {
                    searchValidWords(w, x.next[letterOnBoard - 'A'], prefix + letterOnBoard, words);
                }
                int index = visitingDices.pop();
                marked[index] = false;
            }
        }
    }
}
