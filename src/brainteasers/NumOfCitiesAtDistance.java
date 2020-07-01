package brainteasers;

import java.util.Arrays;
import java.util.Stack;

public class NumOfCitiesAtDistance {

    private static int findCapital(int[] T) {
        int len = T.length;
        int capital = -1;
        for (int i = 0; i < len; i++) {
            if (T[i] == i) {
                capital = i;
            }
        }
        return capital;
    }

    private static int[] getNumberOfCitiesAtDistance(int[] T) {
        if (T == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        int len = T.length;
        int capital = findCapital(T);

        int[] result = new int[len - 1];
        for (int i = 0; i < len; i++) {
            if (i == capital) {
                continue;
            }
            Stack<Integer> path = new Stack<>();
            for (int from = i; from != capital; from = T[from]) {
                path.push(from);
            }
            int dist = path.size() - 1;
            result[dist] = result[dist] + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNumberOfCitiesAtDistance(new int[]{
                9, 1, 4,
                9, 0, 4,
                8, 9, 0,
                1
        })));
    }

}