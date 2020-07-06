package tevalcourse.theory.tries;

import edu.princeton.cs.algs4.Queue;

public class RWayTrie<Value> {

    private static final int R = 256; // ASCII symbols only
    private Node root = new Node();

    private static class Node {
        private Object value;
        private final Node[] next = new Node[R];
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }


    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.value = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.value;
    }


    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
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
        if( x.value != null) {
            q.enqueue(prefix);
        }
        for(char c = 0; c < R; c ++) {
            collect(x.next[c], prefix + c, q);
        }
    }

    public static void main(String[] args) {
        RWayTrie<Integer> trie = new RWayTrie<>();
        trie.put("she", 2);
    }

}
