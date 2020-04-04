package brainteasers.arrays;

import java.util.Arrays;
import java.util.List;

public class RotateMatrix {

    private static void rotate(List<List<Integer>> a) {
        int S = a.size() - 1;
         for (int i = 0; i < a.size() / 2; i++) {
            for (int j = i; j < S - i; ++j) {
                int temp1 = a.get(S - j).get(i);
                int temp2 = a.get(S - i).get(S - j);
                int temp3 = a.get(j).get(S - i);
                int temp4 = a.get(i).get(j);

                // swap
                a.get(i).set(j, temp1);
                a.get(S - j).set(i, temp2);
                a.get(S - i).set(S - j, temp3);
                a.get(j).set(S - i, temp4);
            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4));
        rotate(matrix);
        System.out.println(matrix);

        List<List<Integer>> matrix1 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9));
        rotate(matrix1);
        System.out.println(matrix1);
    }
}
