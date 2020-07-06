package tevalcourse.theory;

import java.util.Arrays;

/*
   Strings:
   - charAt, length and substring -> const time
   - concat - O(N)


   Key-indexed counting:
   - Applications:
        - phone numbers
        - sorting by first letter
        - class rosters
        - as sub-routine of another sorting algorithm
   - Implementation - N + R time and mem, where R is the number of different items in the array
        - count the frequencies of each item using key as index
        - compute frequencies by accumulating the sum along the way - keep in count array
        - use the count array using key as index and move over to an aux array, increasing the count for the next occurrance
        - copy over the aux array into the original array

   - LSD Radix sort (least significance digit first string sorting)
        - consider chars from right to left

   - MSD Radix sort (most significance digit first string sorting)
        - partition array into R pieces according to the first char using key-indexed counting
        - recursively sort all strings that start with each char
        - not really efficient for unicode chars, need count array for each iteration
            => cut off to insertion sort for the smaller subarrays is better

   - 3 way string quicksort
        - do a 3 way partitioning on the dth char - <, = and > of the char
        - less overhead than the R-way partitioning of the MSD

   - Suffix Arrays
        - Applications:
            - find longest repeated substring
            - find word and context around it - search

 */
public class RadixSorts {

    private final static int R = 256; // possible chars - ASCII

    private static void lsdStringSort(String[] input, int len) {
        int n = input.length;
        String[] aux = new String[n];

        for (int d = len - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            // count frequencies of each char using the char as index
            for (int i = 0; i < n; i++) {
                count[input[i].charAt(d) + 1]++;
            }
            // compute the accumulated count frequencies
            for (int j = 0; j < R; j++) {
                count[j + 1] += count[j];
            }
            // sort into the aux array and increase the count
            for (int i = 0; i < n; i++) {
                aux[count[input[i].charAt(d)]++] = input[i];
            }
            // copy back into the original array
            for (int i = 0; i < n; i++) {
                input[i] = aux[i];
            }
        }
    }

    // for strings of variable length use overloaded:
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    private static void msdStringSort(String[] a) {
        String[] aux = new String[a.length];
        msdStringSort(a, aux, 0, a.length - 1, 0);
    }

    private static void msdStringSort(String[] a, String[] aux, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        int[] count = new int[R + 2];
        // count frequencies of each char using the char as index
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        // compute the accumulated count frequencies
        for (int j = 0; j < R + 1; j++) {
            count[j + 1] += count[j];
        }
        // sort into the aux array and increase the count
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        // copy back into the original array
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }
        for (int i = 0; i < R; i++) {
            msdStringSort(a, aux, lo + count[i], lo + count[i + 1], d + 1);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void threeWaySort(String[] a) {
        threeWaySort(a, 0, a.length - 1, 0);
    }

    private static void threeWaySort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        threeWaySort(a, lo, lt - 1, d);
        if (v >= 0) {
            threeWaySort(a, lt, gt, d + 1);
        }
        threeWaySort(a, gt + 1, hi, d);
    }

    // variation - involve double the amount of chars on each iteration
    private static String longestRepeatedSubstring(String s) {
        int n = s.length();

        String[] suffixes = new String[n];
        for (int i = 0; i < n - 1; i++) {
            suffixes[i] = s.substring(i, n);
        }

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i< n-1; i++) {
            int len = lcp(suffixes[i], suffixes[i+1]);
            if(len > lrs.length()) {
                lrs = suffixes[i].substring(0, len);
            }
        }

        return lrs;
    }

    private static int lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return i;
        }
        return n;
    }
}
