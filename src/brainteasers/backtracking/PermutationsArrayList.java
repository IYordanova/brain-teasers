package brainteasers.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PermutationsArrayList {

    public static ArrayList<ArrayList<Integer>> permuteSol1(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(A));

        int size = A.size();
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = 0;
        }

        int i = 0;
        while (i < size) {
            if (indexes[i] < i) {
                swap(A, i % 2 == 0 ? 0 : indexes[i], i);
                result.add(new ArrayList<>(A));
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }

        return result;
    }


    public static ArrayList<ArrayList<Integer>> permuteSol2(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        permuteHelper(result, A, 0, A.size());
        return result;
    }


    private static void permuteHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> A, int from, int to) {
        if (from == to) {
            result.add(new ArrayList<>(A));
        }
        for (int i = from; i < to; i++) {
            swap(A, from, i);
            permuteHelper(result, A, from + 1, to);
            swap(A, from, i);
        }
    }


    private static void swap(ArrayList<Integer> A, int a, int b) {
        int tmp = A.get(a);
        A.set(a, A.get(b));
        A.set(b, tmp);
    }


    public static void main(String[] args) {
        System.out.println(permuteSol1(new ArrayList<>(Arrays.asList(1, 2, 3))));
        System.out.println(permuteSol1(new ArrayList<>(Collections.singletonList(1))));
        System.out.println(permuteSol1(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));

        System.out.println(permuteSol2(new ArrayList<>(Arrays.asList(1, 2, 3))));
        System.out.println(permuteSol2(new ArrayList<>(Collections.singletonList(1))));
        System.out.println(permuteSol2(new ArrayList<>(Arrays.asList(1, 2, 3, 4))));
    }


}
