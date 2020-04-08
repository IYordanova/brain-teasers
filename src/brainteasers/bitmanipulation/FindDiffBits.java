package brainteasers.bitmanipulation;

import java.util.Arrays;
import java.util.List;

public class FindDiffBits {


    private static int cntBits(List<Integer> A) {
        int result = 0;
        int size = A.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                long count = Integer.toBinaryString(A.get(i) ^ A.get(j))
                        .chars()
                        .filter(ch -> ch == '1')
                        .count();
                result += 2 * count;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(cntBits(Arrays.asList(2, 7)));
        System.out.println(cntBits(Arrays.asList(1, 3, 5)));
//        System.out.println(cntBits(Arrays.asList(5, 5, 2, 2, 9, 6, 8, 8, 6, 9, 1)));
    }


}
