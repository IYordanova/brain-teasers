package brainteasers.bitmanipulation;

import java.util.Arrays;
import java.util.List;

public class SingleInArrayAllRestTwice {


    private static int singleNumber(final List<Integer> A) {
        int size = A.size();
        int res = A.get(0);
        System.out.println(Integer.toBinaryString(res));
        for (int i = 1; i < size; i++) {
            res = res ^ A.get(i);
            System.out.println(Integer.toBinaryString(res));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(Arrays.asList(1, 2, 2, 3, 1)));
        System.out.println(singleNumber(Arrays.asList(1, 2, 2)));
        System.out.println(singleNumber(Arrays.asList(5, 5, 2, 2, 9, 6, 8, 8, 6, 9, 1)));
    }


}
