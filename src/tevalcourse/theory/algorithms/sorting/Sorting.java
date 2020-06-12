package tevalcourse.theory.algorithms.sorting;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Sorting {

    /* Selection Sort:
       - in each iteration find the min remaining and swap with the i-th
       - N^2/2 compares and N exchanges
       - doesn't matter what the initial order is - always needs to go over all to check
     */

    private void selectionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    private boolean less(Comparable comparable, Comparable comparable1) {
        return comparable.compareTo(comparable1) < 0;
    }

    private void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }


    /* Insertion Sort
       - 2 pointers, move i to j to the left
       - everything to the left it's already sorted, everything to the right we haven't yet seen, don't case
       - 1/4N^2 compares and 1/4N^2 exchanges on average
       - depend on initial order of the data
                - if already sorted - just validates it with N-1 compares, doesn't exchange
                - if it's desc is worst case scenario
                - if partially in order (has inversions) - linear time
     */

    private void insertionSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /* Shell Sort
       - h - sorted array - move more than one position
       - algorithm is ~ to the insertion one but with h heaps
       - increment sequence choice
            -> 3x + 1
       - N^(3/2) comparisons with the above sequence and
     */

    private void shellSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h - 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); i -= h) {
                    exch(a, j, j - h);
                }
                h = h / 3;
            }
        }
    }

    /* Shuffling
       -
     */

    private void shuffle(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }


    /* Convex Hull - smallest polygon enclosing all points on a plane
       -
     */


    /* Merge Sort
       - split in half
       - recursively sort the 2 halves
       - merge the sorted halves
       - uses at most NlgN compares and 6NlgN array accesses
       - need extra memory - for the aux array
       - possible optimizations:
            - cutoff - use insertion sort instead when array less than 7 for example:
                adding to sort:
                  if(hi<=lo+CUTOFF-1) {
                    insertionSort(a, lo, hi)
                    return;
                  }
            - stop if already sorted - if biggest item in first half less than the smallest on the right
                adding before the call to merge in sort:
                  if(!less(a[mid+1], a[mid])) {
                    return;
                  }
            - eliminate copy to the aux array - switching between the roles of the 2
                  in the sort:
                    sort(aux, a, lo, mid);
                    sort(aux, a, mid + 1, hi);
                    merge(a, aux, lo, mid, hi);
                  in the merge:
                    aux[k] = a[x]..
     */

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
//        assert isSorted(a, lo, mid);
//        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i < mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
//        assert isSorted(a, lo, hi);
    }

    /* Bottom-up Merge Sort
       - pass through the array merging sub-arrays of size 1, then 2, 4, 8 ect
     */

    private void sortBottomUp(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    /* Comparator interface
       - allows alternative order
       - use to extend the implementation of the above algorithms
     */

    /* Stable sort
       - preserves the relative order of items with equal keys
       - insertion and merge sort are stable
       - selection sort and shell sort are NOT stable
     */


    /* Combinations
     */

    private static void combinationUtil(int[] arr, int[] data, int start, int end, int index, int r) {
        if (index == r) {
            System.out.println(Arrays.toString(data));
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int r = 4;
        combinationUtil(arr, new int[r], 0, arr.length - 1, 0, r);
    }

}
