package brainteasers.arrays;

import java.util.Arrays;
import java.util.List;

public class MinAbsoluteDifference {


    private static int solve(List<Integer> A, List<Integer> B, List<Integer> C) {
        int diff = Integer.MAX_VALUE;

        int ai = 0;
        int bi = 0;
        int ci = 0;

        int aSize = A.size();
        int bSize = B.size();
        int cSize = C.size();

        while (ai < aSize && bi < bSize && ci < cSize) {
            int maximum = Math.max(A.get(ai), Math.max(B.get(bi), C.get(ci)));
            int minimum = Math.min(A.get(ai), Math.min(B.get(bi), C.get(ci)));

            if (maximum - minimum < diff) {
                diff = maximum - minimum;
            }

            if (diff == 0) {
                break;
            }

            if (A.get(ai) == minimum) {
                ai++;
            } else if (B.get(bi) == minimum) {
                bi++;
            } else {
                ci++;
            }
        }

        return diff;
    }

    public static void main(String[] args) {
        System.out.println(solve(
                Arrays.asList(1, 4, 5, 8, 10),
                Arrays.asList(6, 9, 15),
                Arrays.asList(2, 3, 6, 6)));
    }


}
