package tevalcourse.arraay_order_check;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.function.BiPredicate;

public class ArrayOrder extends BaseTestHelper {

    public static int checkArrayOrder(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        if (arr[0] > arr[1]) {
            if (isSortedDesc(arr)) {
                return -1;
            }
        } else {
            if (isSortedAsc(arr)) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean isSortedAsc(int[] array) {
        return isSorted(array, (a, b) -> a > b);
    }

    private static boolean isSortedDesc(int[] array) {
        return isSorted(array, (a, b) -> a < b);
    }

    private static boolean isSorted(int[] array, BiPredicate<Integer, Integer> inOrder) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            if (inOrder.test(array[i], array[i + 1])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        int[] numbers = readFile("secondLargest_1")
                .stream()
                .mapToInt(Integer::parseInt).toArray();

        long start1 = System.nanoTime();
        System.out.println(String.format("Is sorted %d, done in %d", checkArrayOrder(numbers), (System.nanoTime() - start1) / 1000000));

        System.out.println(String.format("Is sorted %d", checkArrayOrder(null)));

        numbers = new int[]{1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{-10, -9, -8, -7, -6, -5, -4, -3, -2, -1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{-1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{1, 2};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{2, 1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{1, 1, 1, 1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{-1, -1, -1};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));

        numbers = new int[]{4, 11, 6, 2, 12};
        System.out.println(String.format("Is sorted %d", checkArrayOrder(numbers)));
    }
}
