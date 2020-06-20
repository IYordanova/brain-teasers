package tevalcourse.theory.symboltable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
    - 2-3 trees
         - guarantees logarithmic time
         - allow 1 or 2 keys and consequentially 2 or 3 children, where middle is in-between the 2 keys
         - perfect balance - every path has same length
         - symmetric
         - adding new value to existing 2 key node -> make it 3 key node temporarily then move the middle to the parent and split the children in 2
         if that's the root, create new -> the height grows
         - complicated to maintain and implement


    - red-black trees (left-leaning)
         - represent 2-3 tree as a binary search tree
         - use internal left-leaning links for 3 nodes (red links)
         - no node has 2 red links
         - every path from the root to the null link has same number of black links
         - red links lean left
         - search is exactly the same as for BST - ignoring type of link
         - most other operations are same as well, insert is diff

     - b-trees
         - allow not only 2-3 keys but up to M-1 key-link pairs per node
         - at least 2 pairs at root
         - at least M/2 pairs in other nodes
         - external nodes contain client keys
         - internal nodes contain copies of keys to guide search
         - M is in the thousands, fitting in to 1 page in the disk
         - insertions and search take between logM-1N and logM/2N probes
         - all internal nodes have between M/2 and M-1 links
         - in practice number of probes is at most 4
         - optimization - always keep the root page in memory

 */
public class StRbTree<Key extends Comparable<Key>, Value> {
    class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private boolean red; // in the node to which the red link comes into

        public Node(Key key, Value value, boolean red) {
            this.key = key;
            this.value = value;
            this.red = red;
        }
    }

    private Node root;

    private Node rotateLeft(Node n) {
        assert n.right.red;
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.red = n.red;
        n.red = true;
        return x;
    }

    private Node rotateRight(Node n) {
        assert n.left.red;
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        x.red = n.red;
        n.red = true;
        return x;
    }

    private void flipColors(Node n) {
        assert !n.red;
        assert n.left.red;
        assert n.right.red;
        n.red = true;
        n.left.red = false;
        n.right.red = false;
    }

    // always insert and put a red link to the parent, if on the right rotate left
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, true);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.value = val;
        }
        if (h.right.red && !h.left.red) {
            h = rotateLeft(h);
        }
        if (h.left.red && h.left.left.red) {
            h = rotateRight(h);
        }
        if (h.left.red && h.right.red) {
            flipColors(h);
        }

        return h;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        // TODO:
        return false;
    }

    public boolean isEmpty() {
        // TODO:
        return false;
    }

    public int size() {
        // TODO:
        return 0;
    }


    public Key min() {
        // TODO:
        return null;
    }

    public Key max() {
        // TODO:
        return null;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }


    public Key ceiling() {
        // TODO:
        return null;
    }

    public Key select(int k) {
        // TODO:
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key k) {
        if (x == null) return null;
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, k);
        } else if (cmp > 0) {
            x.right = delete(x.right, k);
        } else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            Node t = x;
//            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public void deleteMax() {

    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        // TODO:
        return null;
    }


    public static void main(String[] args) {
        StRbTree<String, Integer> st = new StRbTree<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
