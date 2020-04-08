package brainteasers.bitmanipulation;

import java.util.Arrays;
import java.util.List;

public class MinXor {


    private static int findMinXor(List<Integer> A) {
        int minXor = Integer.MAX_VALUE;
        int size = A.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                minXor = Math.min(minXor, A.get(i) ^ A.get(j));
            }
        }
        return minXor;
    }

    public static void main(String[] args) {
        System.out.println(findMinXor(Arrays.asList( 15, 5, 1, 10, 2)));
//        System.out.println(findMinXor(Arrays.asList(1, 2, 2)));
//        System.out.println(findMinXor(Arrays.asList(5, 5, 2, 2, 9, 6, 8, 8, 6, 9, 1)));
    }


}
