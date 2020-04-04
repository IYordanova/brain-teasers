package brainteasers.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AntiDiagonal {
    private static ArrayList<ArrayList<Integer>> diagonal(List<List<Integer>> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int size = A.size();
        int col = 0;
        int row = 0;
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> diagonal = new ArrayList<>();
            result.add(diagonal);
            for (int k = col, r = row; k >= 0 && r < col + 1; k--, r++) {
                diagonal.add(A.get(r).get(k));
            }
            col++;
        }

        row = 1;
        col = size - 1;
        for (int i = 0; i < size - 1; i++) {
            ArrayList<Integer> diagonal = new ArrayList<>();
            result.add(diagonal);
            for (int k = col, r = row; k >= 0 && r < col + 1; k--, r++) {
                diagonal.add(A.get(r).get(k));
            }
            row++;
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(diagonal(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9))));
        System.out.println(diagonal(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4))));
        System.out.println(diagonal(Arrays.asList(
                Arrays.asList(1, 2, 3, 14, 15),
                Arrays.asList(4, 5, 6, 17, 18),
                Arrays.asList(7, 8, 9, 20, 21),
                Arrays.asList(10, 11, 12, 23, 24),
                Arrays.asList(13, 14, 15, 26, 27))));
    }


}
