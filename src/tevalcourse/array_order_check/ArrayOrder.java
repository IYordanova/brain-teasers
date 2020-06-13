package tevalcourse.array_order_check;

import tevalcourse.BaseTest;

import java.util.function.BiPredicate;

public class ArrayOrder extends BaseTest {

    /**
     * Method checking whether an array passed as argument is sorted
     * @param intArray - an array of integers
     * @return an int indicating whether the array is sorted and in what direction, possible values are:
     *   1 -> input array is sorted in ascending order
     *  -1 -> input array is sorted in descending order
     *   0 -> input array is *not* sorted or the input is null or it's an array with less than 2 items
     */
    public static int checkArrayOrder(int[] intArray) {
        if (intArray == null || intArray.length < 2) {
            return 0;
        }
        if (intArray[0] > intArray[1]) {
            if (isSortedDesc(intArray)) {
                return -1;
            }
        } else {
            if (isSortedAsc(intArray)) {
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

    private static boolean isSorted(int[] intArray, BiPredicate<Integer, Integer> inOrder) {
        int length = intArray.length;
        for (int i = 0; i < length - 1; i++) {
            if (inOrder.test(intArray[i], intArray[i + 1])) {
                return false;
            }
        }
        return true;
    }

}
