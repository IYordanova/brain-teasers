package brainteasers.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRangeInArray {

    private static ArrayList<Integer> searchRange(final List<Integer> A, int B) {
        int index = binarySearch(A, 0, A.size() - 1, B);
        if (index == -1) {
            return new ArrayList<>(Arrays.asList(-1, -1));
        }
        int beginning = index - 1;
        int ending = index + 1;
        Integer start = null;
        Integer end = null;
        while (start == null || end == null) {
            if (beginning >= 0 && A.get(beginning) == B) {
                beginning--;
            } else {
                start = beginning + 1;
            }
            if (ending < A.size() && A.get(ending) == B) {
                ending++;
            } else {
                end = ending - 1;
            }
        }
        return new ArrayList<>(Arrays.asList(start, end));
    }

    private static int binarySearch(List<Integer> A, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            Integer minNum = A.get(mid);
            if (minNum == x) {
                return mid;
            }
            if (minNum > x) {
                return binarySearch(A, l, mid - 1, x);
            }
            return binarySearch(A, mid + 1, r, x);
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(searchRange(Arrays.asList(5, 7, 7, 8, 8, 10), 8));
        System.out.println(searchRange(Arrays.asList(5, 17, 100, 111), 3));
        System.out.println(searchRange(Arrays.asList(1, 7, 7, 8, 8, 10), 1));
        System.out.println(searchRange(Arrays.asList(5, 17, 100, 111, 199, 199, 199), 199));
        System.out.println(searchRange(Arrays.asList(5, 5, 17, 100, 111, 199, 199, 199), 5));
    }


}
