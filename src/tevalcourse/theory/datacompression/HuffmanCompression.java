package tevalcourse.theory.datacompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

/*
   - goal to reduce size of a file - reduce size in memory and time while transmitting
   - lossless compression:
   - Run-length encoding - long runs of repeated bits
        - represent those as counts
        - applications - jpeg, itu-t, fax
   - Huffman compression:
        - variable length codes
        - to avoid ambiguity:
            - ensure no codeword is a prefix of another
            - agree on special stop char
        - can be represented in a trie or table
        - Shannon-Fano codes - chars appear with diff frequencies, so use fewer bits for the ones that are most freq
        - 1st count the freq of every char in the input
        - 2nd select the 2 tries with min weight
        - 3rd merge them into one and aum the weight as a value for the parent
        - applications - jpeg, mp3, pdf, gzip
        - running time - N+RlogR
 */
public class HuffmanCompression {
    private static final int R = 26; // alphabet size

    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private static Node c(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < R; i++) {
            if (freq[i] > 0) {
                pq.insert(new Node(i, freq[i], null, null));
            }
        }

        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
        }

        return pq.delMin();
    }


    public void expand() {
        Node root = readTrie();
        int n = BinaryStdIn.readInt();
        for (int i = 0; i < n; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (!BinaryStdIn.readBoolean()) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            BinaryStdOut.write(x.ch, 8);
        }
        BinaryStdOut.close();
    }

    private Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            char ch = BinaryStdIn.readChar(8);
            return new Node(ch, 0, null, null);
        }
        Node x = readTrie();
        Node y = readTrie();
        return new Node('\0', 0, x, y);
    }


    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }
}
