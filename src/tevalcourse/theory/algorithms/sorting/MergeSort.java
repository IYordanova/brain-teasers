package tevalcourse.theory.algorithms.sorting;

public class MergeSort {

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

    public void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
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
            if (i > mid) {
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

    private boolean less(Comparable comparable, Comparable comparable1) {
        return comparable.compareTo(comparable1) < 0;
    }

    private void exch(Comparable[] a, int i, int min) {
        Comparable swap = a[i];
        a[i] = a[min];
        a[min] = swap;
    }
}
