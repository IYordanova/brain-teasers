package tevalcourse.theory.sorting;

import edu.princeton.cs.algs4.StdRandom;


public class QuickSort {
    /*
        - steps:
             - shuffle array
             - partition the array so that
                - a[j] is in place
                - no larger entries to the left
                - no smaller to the right
            - sort each piece recursively
        - doesn't need the extra space used in merge sort
        - average case: compares = 2NlnN and exchanges = 1/3NlnN
        - worst case - N^2 - but the shuffle guarantees this won't happen
        - not stable
        - ways to optimizer:
            - cutoff - use insertion sort for smaller array, too much overhead ~ 10 - 20
            - estimate partitioning element to be at the middle - median of the sample
     */

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private static boolean less(Comparable comparable, Comparable comparable1) {
        return comparable.compareTo(comparable1) < 0;
    }

    private static void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }

    /*
      Selection:
      - ~ quicksort but stop when k is found
     */

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    /* When multiple keys
        - three-way partitioning
            - smaller
            - equal
            - bigger
     */

    private static void sortThreeWay(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
