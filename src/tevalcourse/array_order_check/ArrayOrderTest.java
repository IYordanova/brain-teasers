package tevalcourse.array_order_check;

import tevalcourse.BaseTest;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayOrderTest extends BaseTest {

    private static void generateBigInputFile() throws IOException {
        Random r = new Random();
        int max = 1000000;
        List<String> lines = IntStream.range(1, max)
                .boxed()
                .map(i -> String.valueOf(r.nextInt(max)))
                .collect(Collectors.toList());
        writeFile("bigArray", lines);
    }


    public static void main(String[] args) throws IOException {
        generateBigInputFile();

        int[] bigArrayOfNumbers = readFile("bigArray")
                .stream()
                .mapToInt(Integer::parseInt).toArray();

        long start = System.nanoTime();
        int isSorted = ArrayOrder.checkArrayOrder(bigArrayOfNumbers);
        long end = System.nanoTime();
        assert isSorted == 0;
        long runTime = (end - start) / 1000000;
        assert runTime < 10;

        isSorted = ArrayOrder.checkArrayOrder(null);
        assert isSorted == 0;

        int[] singleElementArray = new int[]{1};
        isSorted = ArrayOrder.checkArrayOrder(singleElementArray);
        assert isSorted == 0;

        int[] ascSortedPositiveNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        isSorted = ArrayOrder.checkArrayOrder(ascSortedPositiveNumbers);
        assert isSorted == 1;

        int[] descSortedPositiveNumbers = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        isSorted = ArrayOrder.checkArrayOrder(descSortedPositiveNumbers);
        assert isSorted == -1;

        int[] ascSortedNegativeNumbers = new int[]{-10, -9, -8, -7, -6, -5, -4, -3, -2, -1};
        isSorted = ArrayOrder.checkArrayOrder(ascSortedNegativeNumbers);
        assert isSorted == 1;

        int[] descSortedNegativeNumbers = new int[]{-1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        isSorted = ArrayOrder.checkArrayOrder(descSortedNegativeNumbers);
        assert isSorted == -1;

        int[] ascSortedTwoElementArray = new int[]{1, 2};
        isSorted = ArrayOrder.checkArrayOrder(ascSortedTwoElementArray);
        assert isSorted == 1;

        int[] descSortedTwoElementArray = new int[]{2, 1};
        isSorted = ArrayOrder.checkArrayOrder(descSortedTwoElementArray);
        assert isSorted == -1;

        int[] duplicatePositiveNumbers = new int[]{1, 1, 1, 1};
        isSorted = ArrayOrder.checkArrayOrder(duplicatePositiveNumbers);
        assert isSorted == 1;

        int[] duplicateNegativeNumbers = new int[]{-1, -1, -1, -1};
        isSorted = ArrayOrder.checkArrayOrder(duplicateNegativeNumbers);
        assert isSorted == 1;

        int[] unsortedArray = new int[]{4, 11, 6, 2, 12};
        isSorted = ArrayOrder.checkArrayOrder(unsortedArray);
        assert isSorted == 0;
    }
}
