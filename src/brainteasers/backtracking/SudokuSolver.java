package brainteasers.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuSolver {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final char NO_VALUE = '.';


    public static void solveSudoku(ArrayList<ArrayList<Character>> a) {
        solve(a);
    }

    private static boolean solve(ArrayList<ArrayList<Character>> a) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (a.get(row).get(column) == NO_VALUE) {
                    for (int k = 1; k <= 9; k++) {
                        a.get(row).set(column, Character.forDigit(k, 10));
                        if (isValid(a, row, column) && solve(a)) {
                            return true;
                        }
                        a.get(row).set(column, NO_VALUE);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(ArrayList<ArrayList<Character>> a, int row, int column) {
        return (rowConstraint(a, row)
                && columnConstraint(a, column)
                && subsectionConstraint(a, row, column));
    }

    private static boolean rowConstraint(ArrayList<ArrayList<Character>> a, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(0, BOARD_SIZE)
                .allMatch(column -> checkConstraint(constraint, a.get(row).get(column)));
    }

    private static boolean columnConstraint(ArrayList<ArrayList<Character>> a, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(0, BOARD_SIZE)
                .allMatch(row -> checkConstraint(constraint, a.get(row).get(column)));
    }

    private static boolean checkConstraint(boolean[] constraint, char squareVal) {
        if (squareVal != NO_VALUE) {
            int num = Character.getNumericValue(squareVal);
            if (!constraint[num - 1]) {
                constraint[num - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean subsectionConstraint(ArrayList<ArrayList<Character>> a, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(constraint, a.get(r).get(c))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> a = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList('5', '3', '.', '.', '7', '.', '.', '.', '.')),
                new ArrayList<>(Arrays.asList('6', '.', '.', '1', '9', '5', '.', '.', '.')),
                new ArrayList<>(Arrays.asList('.', '9', '8', '.', '.', '.', '.', '6', '.')),

                new ArrayList<>(Arrays.asList('8', '.', '.', '.', '6', '.', '.', '.', '3')),
                new ArrayList<>(Arrays.asList('4', '.', '.', '8', '.', '3', '.', '.', '1')),
                new ArrayList<>(Arrays.asList('7', '.', '.', '.', '2', '.', '.', '.', '6')),

                new ArrayList<>(Arrays.asList('.', '6', '.', '.', '.', '.', '2', '8', '.')),
                new ArrayList<>(Arrays.asList('.', '.', '.', '4', '1', '9', '.', '.', '5')),
                new ArrayList<>(Arrays.asList('.', '.', '.', '.', '8', '.', '.', '7', '9'))
        ));
        solveSudoku(a);
        System.out.println("ada");
    }


}
