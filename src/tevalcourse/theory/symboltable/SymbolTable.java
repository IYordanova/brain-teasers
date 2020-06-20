package tevalcourse.theory.symboltable;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolTable<Key extends Comparable<Key>, Value> {
    /*
       - keys associated with values => 2 operations
       - insert key-value pair
       - get by key
       - applications: index, table, dictionary, etc
       - values are not null
       - get returns null if key not present
       - put overrides old value if key already exists
       - assume keys are comparable
       - assume generic type -> use equals
       - assume generic type -> use equals and hashCode

       - equals
            - reflexive
            - symmetric
            - transitive
            - non-null

     */

    /*
       Simple implementations
       - use linked list where each noe contains the pair Key-Value
       - use 2 parallel arrays - the keys is sorted, use the key to binary search the value

       Binary search tree implementation
       - binary tree - every node has 2 connections, can be null
       - symmetric order - each node has key, left children smaller, right bigger
     */


    private Key[] keys;
    private Value[] vals;
    private int N;

    public SymbolTable() {
    }

    public void put(Key key, Value val) {

    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void delete(Key key) {

    }

    public boolean contains(Key key) {
        // TODO:
        return false;
    }

    public boolean isEmpty() {
        // TODO:
        return true;
    }

    public int size() {
        // TODO:
        return 0;
    }

    public Iterable<Key> keys() {
        // TODO:
        return null;
    }

    public Key min() {
        // TODO:
        return null;
    }

    public Key max() {
        // TODO:
        return null;
    }

    public Key floor() {
        // TODO:
        return null;
    }

    public Key ceiling() {
        // TODO:
        return null;
    }

    public Key select(int k) {
        // TODO:
        return null;
    }

    public void deleteMin() {
        // TODO:
    }

    public void deleteMax() {
        // TODO:
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        // TODO:
        return null;
    }


    public static void main(String[] args) {
        SymbolTable<String, Integer> st = new SymbolTable<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
