package tevalcourse.second_largest;

import tevalcourse.BaseTestHelper;

import java.io.IOException;


public class SecondLargestPlayground extends BaseTestHelper {


    private static int getSecondLargest2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > first) {
                second = first;
                first = value;
            } else if (value > second && value != first) {
                second = value;
            }
        }

        if (second == Integer.MIN_VALUE) {
            return -1;
        }

        return second;
    }

    public static void main(String[] args) throws IOException {
        int[] numbers = readFile("secondLargest_1")
                .stream()
                .mapToInt(Integer::parseInt).toArray();

        long start1 = System.nanoTime();
        System.out.println(String.format("Element is %d, done in %d", getSecondLargest2(numbers), (System.nanoTime() - start1) / 1000000));

        try {
            getSecondLargest2(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            numbers = new int[]{1};
            getSecondLargest2(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        numbers = new int[]{1, 5, 4, 7, 9, 0, 3, 3, 4, 3, 2, 6, 7, 8, 3, 2, 10};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{-1, -5, -4, -7, -9, 0, -3, -3, -4, -3, -2, -6, -7, -8, -3, -2, -10};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{5, -2, -2, -3, -4, -3};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{1, 2};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{4, 1, 1, 1, 1};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{3, 10, 10, 10};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));

        numbers = new int[]{3, 10, 5, 10};
        System.out.println(String.format("Element is %d", getSecondLargest2(numbers)));
    }

}

