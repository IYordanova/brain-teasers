package tevalcourse.second_largest;

import tevalcourse.BaseTest;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SecondLargestTest extends BaseTest {

    private static void generateBigInputFile() throws IOException {
        Random r = new Random();
        int max = 1000000;
        List<String> lines = IntStream.range(1, max)
                .boxed()
                .map(i -> String.valueOf(r.nextInt(max)))
                .collect(Collectors.toList());
        writeFile("secondLargest", lines);
    }


    public static void main(String[] args) throws IOException {
        generateBigInputFile();

        int[] bigArrayOfNumbers = readFile("secondLargest")
                .stream()
                .mapToInt(Integer::parseInt).toArray();

        long start = System.nanoTime();
        int secondLargest = SecondLargest.getSecondLargest(bigArrayOfNumbers);
        long end = System.nanoTime();
        assert secondLargest == 999998;
        long runTime = (end - start) / 1000000;
        assert runTime < 10;

        secondLargest = SecondLargest.getSecondLargest(null);
        assert secondLargest == -1;

        int[] singleElementArray = new int[]{1};
        secondLargest = SecondLargest.getSecondLargest(singleElementArray);
        assert secondLargest == -1;

        int[] twoElementArray = new int[]{1, 2};
        secondLargest = SecondLargest.getSecondLargest(twoElementArray);
        assert secondLargest == 1;

        int[] positiveNumbers = new int[]{1, 5, 4, 7, 9, 0, 3, 3, 4, 3, 2, 6, 7, 8, 3, 2, 10};
        secondLargest = SecondLargest.getSecondLargest(positiveNumbers);
        assert secondLargest == 9;

        int[] negativeNumbers = new int[]{-1, -5, -4, -7, -9, 0, -3, -3, -4, -3, -2, -6, -7, -8, -3, -2, -10};
        secondLargest = SecondLargest.getSecondLargest(negativeNumbers);
        assert secondLargest == -1;

        int[] mixedPositiveNegativeNumbers = new int[]{5, -2, 1, -3, -4, -3};
        secondLargest = SecondLargest.getSecondLargest(mixedPositiveNegativeNumbers);
        assert secondLargest == 1;

        int[] someDuplicateNumbersStart = new int[]{1, 1, 1, 1, 5};
        secondLargest = SecondLargest.getSecondLargest(someDuplicateNumbersStart);
        assert secondLargest == 1;

        int[] someDuplicateNumbersEnd = new int[]{3, 10, 10, 10};
        secondLargest = SecondLargest.getSecondLargest(someDuplicateNumbersEnd);
        assert secondLargest == 3;

        int[] someDuplicateNumbersInbetween = new int[]{3, 10, 5, 10};
        secondLargest = SecondLargest.getSecondLargest(someDuplicateNumbersInbetween);
        assert secondLargest == 5;

        int[] allDuplicateNumbers = new int[]{1, 1, 1, 1};
        secondLargest = SecondLargest.getSecondLargest(allDuplicateNumbers);
        assert secondLargest == -1;
    }

}

