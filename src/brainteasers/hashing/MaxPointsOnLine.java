package brainteasers.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MaxPointsOnLine {

    private static int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a == null || b == null || a.size() == 0 || b.size() == 0) {
            return 0;
        }
        HashMap<Double, Integer> result = new HashMap<>();
        int max = 0;
        int aSize = a.size();

        for (int i = 0; i < aSize; i++) {
            int duplicate = 1;
            int vertical = 0;
            for (int j = i + 1; j < aSize; j++) {
                if (a.get(i).equals(a.get(j))) {
                    if (b.get(i).equals(b.get(j))) {
                        duplicate++;
                    } else {
                        vertical++;
                    }
                } else {
                    double slope = b.get(j).equals(b.get(i)) ? 0.0
                            : (1.0 * (b.get(j) - b.get(i)))
                            / (a.get(j) - a.get(i));

                    result.merge(slope, 1, Integer::sum);
                }
            }

            for (Integer count : result.values()) {
                if (count + duplicate > max) {
                    max = count + duplicate;
                }
            }

            max = Math.max(vertical + duplicate, max);
            result.clear();
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxPoints(new ArrayList<>(Arrays.asList(1, 1)), new ArrayList<>(Arrays.asList(2, 2))));
    }

}
