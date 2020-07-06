package tevalcourse.theory.tries;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

/*
each node - 3 children, <, = and >
 */
public class TernaryTrie<Value> {

    private class Node {
        private Value value;
        private char ch;
        private Node left, middle, right;
    }

    private Node root;

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char ch = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.ch = ch;
        }
        if( ch < x.ch) {
            x.left = put(x.left, key, val, d);
        } else if(ch > x.ch) {
            x.right = put(x.right, key, val, d);
        } else if(d < key.length() - 1) {
            x.middle = put(x.middle, key, val, d + 1);
        } else {
            x.value = val;
        }
        return x;
    }


}
