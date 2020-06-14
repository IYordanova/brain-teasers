package tevalcourse.theory.priorityqeueues;

/*
    Binary heap
    - complete binary tree - perfectly balanced except for the bottom level
    - height is lnN
    - height increases only when N is a power of 2
    - array representation :
        - children keys no bigger than the parent's
        - indices start at 1
        - take nodes in levels
        - a[1] is the largest key
        - parent of k is k/2
        - children of k are 2k and 2k + 1
 */
public class MaxPriorityQueue<Key extends Comparable<Key>> {

    private final Key[] pq;
    private int N;

    MaxPriorityQueue(int capacity) {
        this.pq = (Key[]) new Comparable[capacity + 1];
    }


    boolean isEmpty() {
        return N == 0;
    }

    // if a parent becomes smaller than one or both of it's children
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }


    // promotes child to the top if it has become bigger than the parent
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /* Heap sort
      - requires correction in sink, less, exch to account for position 0 - decrement all by 1
      - in-place
      - 2NlgN compares and exchanges
      - it's not stable
      - pointers are all over the place in the memory so no option to cache optimal
    */

    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

}

