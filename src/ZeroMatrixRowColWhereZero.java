import java.util.*;

public class ZeroMatrixRowColWhereZero {

    private static void setZeroes(List<List<Integer>> a) {
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < a.size(); i++) {
            List<Integer> row = a.get(i);
            boolean zeroFound = false;
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).equals(0)) {
                    cols.add(j);
                    zeroFound = true;
                }
            }
            if(zeroFound) {
                setRowZeroes(a, i);
            }
        }
        cols.forEach(col -> setColZeroes(a, col));
    }

    private static void setRowZeroes(List<List<Integer>> a, int row) {
        List<Integer> theRow = a.get(row);
        for (int i = 0; i < theRow.size(); i++) {
            theRow.set(i, 0);
        }
    }

    private static void setColZeroes(List<List<Integer>> a, int col) {
        for (List<Integer> integers : a) {
            integers.set(col, 0);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(1, 0, 1),
                Arrays.asList(1, 1, 1),
                Arrays.asList(1, 1, 1));
        setZeroes(matrix);
        System.out.println(matrix);


        List<List<Integer>> matrix1 = Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(1, 1));
        setZeroes(matrix1);
        System.out.println(matrix1);
    }
}
