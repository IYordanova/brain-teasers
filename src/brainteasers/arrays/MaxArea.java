package brainteasers.arrays;

import java.util.Arrays;
import java.util.List;

public class MaxArea {


    private static int maxArea(List<Integer> A) {
        int size = A.size();
        int max = 0;
        for (int i = 0; i < size; i++) {
            Integer a = A.get(i);
            int j = size - 1;
            while (i < j) {
                int area = (j - i) * Math.min(a, A.get(j));
                if (area > max) {
                    max = area;
                }
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(Arrays.asList(1, 5, 4, 3)));
    }


}
