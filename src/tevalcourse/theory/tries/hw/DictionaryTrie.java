package tevalcourse.theory.tries.hw;

import edu.princeton.cs.algs4.Queue;

public class DictionaryTrie {

    private static final int R = 26; // A-Z only
    private Node root = new Node();

    static class Node {
        String word;
        final Node[] next = new Node[R];
    }

    Node getRoot() {
        return root;
    }

    public void put(String key, String word) {
        root = put(root, key, word, 0);
    }


    private Node put(Node x, String key, String word, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.word = word;
            return x;
        }
        int c = key.charAt(d) - 'A';
        x.next[c] = put(x.next[c], key, word, d + 1);
        return x;
    }

    public String get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.word;
    }


    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        int c = key.charAt(d) - 'A';
        return get(x.next[c], key, d + 1);
    }

    public boolean contains(String key) {
        return get(key) != null;
    }


    public Iterable<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, "", queue);
        return queue;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.word != null) {
            q.enqueue(prefix);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], prefix + c, q);
        }
    }
}
