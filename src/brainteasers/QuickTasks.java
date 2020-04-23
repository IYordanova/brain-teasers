package brainteasers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickTasks {


    public static int familyOfNumbersFindMax(int N) {
        if (N < 10) {
            return N;
        }

        String sortedDigits = Integer.toString(N).chars()
                .map(c -> c - '0')
                .mapToObj(String::valueOf)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining());

        return Integer.parseInt(sortedDigits);
    }


    static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static List<Point> convertToPoints(int[] X, int[] Y) {
        int size = X.length;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            points.add(new Point(X[i], Y[i]));
        }
        points.sort(Comparator.comparingInt(p -> p.x));
        return points;
    }

    public static int maxWidthPathInWoods(int[] X, int[] Y) {
        if (X.length != Y.length) {
            return -1;
        }
        List<Point> points = convertToPoints(X, Y);
        int numTrees = points.size();
        int maxPathWidth = 0;
        for (int i = 0; i < numTrees - 1; i++) {
            int dist = points.get(i + 1).x - points.get(i).x;
            if (dist > maxPathWidth) {
                maxPathWidth = dist;
            }
        }
        return maxPathWidth;
    }

    private static BigDecimal perm(int n) {
        BigDecimal fact = new BigDecimal(1);
        for (int i = 2; i <= n; i++)
            fact = fact.multiply(new BigDecimal(i));
        return fact;
    }

    private static BigDecimal comb(int n, int k) {
        BigDecimal perm = perm(n);
        BigDecimal perm1 = perm(n - k);
        return perm.divide(perm1.multiply(new BigDecimal(k)));
    }

    private static void test(int [] arr){
        long start = System.currentTimeMillis();
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i+1; j < size; j++) {
                int sum = arr[i] + arr[j];
            }
        }
        System.out.println(start - System.currentTimeMillis());
    }


    public static void main(String[] args) {
        System.out.println(familyOfNumbersFindMax(123));
        System.out.println(familyOfNumbersFindMax(533));

        System.out.println(maxWidthPathInWoods(new int[]{1, 8, 7, 3, 4, 1, 8}, new int[]{6, 4, 1, 8, 5, 1, 7}));
        System.out.println(maxWidthPathInWoods(new int[]{5, 5, 5, 7, 7, 7}, new int[]{3, 4, 55, 1, 3, 7}));
        System.out.println(maxWidthPathInWoods(new int[]{6, 10, 1, 4, 3}, new int[]{2, 5, 3, 1, 6}));

        System.out.println(Math.pow(1000, 2));
        System.out.println(perm(1000));
        System.out.println(comb(1000, 2));

        test(IntStream.rangeClosed(1, 1000000000).toArray());
    }

}
