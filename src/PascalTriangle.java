import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PascalTriangle {

    private final static ArrayList<Integer> firstRow = new ArrayList<>(Collections.singletonList(1));
    private final static ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(1, 1));

    private static ArrayList<ArrayList<Integer>> solve(int A) {
        if(A == 0) {
            return new ArrayList<>(new ArrayList<>());
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(A == 1) {
            result.add(firstRow);
            return result;
        }

        if(A == 2) {
            result.add(firstRow);
            result.add(secondRow);
            return result;
        }

        result.add(firstRow);
        result.add(secondRow);
        for (int i = 2; i < A; i++) {
            ArrayList<Integer> nthRow = new ArrayList<>();
            nthRow.add(1);
            for(int j = 0; j < i-1; j ++) {
                ArrayList<Integer> prevRow = result.get(i - 1);
                nthRow.add(prevRow.get(j) + prevRow.get(j+1));
            }
            nthRow.add(1);
            result.add(nthRow);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(5));

    }
}
