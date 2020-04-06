package brainteasers.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSumZero {


    private static ArrayList<ArrayList<Integer>> threeSum(List<Integer> A) {
        Collections.sort(A);

        int size = A.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer one = A.get(i);
            if (i > 0 && one.equals(A.get(i - 1))) {
                continue;
            }

            int j = i + 1;
            int k = size - 1;
            while (j < k) {
                Integer two = A.get(j);
                Integer three = A.get(k);

                if (k < size - 1 && three.equals(A.get(k + 1))) {
                    k--;
                    continue;
                }

                long sum = ((long)one) + ((long)two) + ((long)three);
                if (sum > 0) {
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    result.add(new ArrayList<>(Arrays.asList(one, two, three)));
                    j++;
                    k--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(Arrays.asList(-1, 0, 1, 2, -1, -4)));
        System.out.println(threeSum(Arrays.asList(3, 4, 5, 7, 8, 11)));
        System.out.println(threeSum(Arrays.asList(2147483647, -2147483648, -2147483648, 0, 1)));
        System.out.println(threeSum(Arrays.asList(1, -4, 0, 0, 5, -5, 1, 0, -2, 4, -4, 1, -1, -4, 3, 4, -1, -1, -3)));
    }


}
