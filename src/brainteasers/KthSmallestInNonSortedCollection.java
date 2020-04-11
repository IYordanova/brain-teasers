package brainteasers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KthSmallestInNonSortedCollection {


    private static int kthSmallest(final List<Integer> A, int B) {
        int size = A.size();
        if (B > size) {
            return -1;
        }
        Collections.sort(A);
        if (B == 1) {
            return A.get(0);
        }
        if (B == size) {
            return A.get(size - 1);
        }
        int index = 1;
        for (int i = 1; i < size; i++) {
            if (index == B - 1) {
                return A.get(index);
            }
            if (A.get(i).equals(A.get(index))) {
                index++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(kthSmallest(Arrays.asList(2, 1, 4, 3, 2), 3));
        System.out.println(kthSmallest(Arrays.asList( 8, 16, 80, 55, 32, 8, 38, 40, 65, 18, 15, 45, 50, 38,
                54, 52, 23, 74,  81, 42, 28, 16, 66, 35, 91, 36, 44, 9, 85, 58, 59, 49, 75, 20, 87, 60, 17,
                11, 39, 62, 20, 17, 46, 26,  81, 92 ), 9));
        System.out.println(kthSmallest(Arrays.asList(74, 90, 85, 58, 69, 77, 90, 85, 18, 36), 1));
    }


}
