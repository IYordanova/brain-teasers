package tevalcourse.second_largest;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecondLargestTestGenerator extends BaseTestHelper {

    private static void generateBigInputFile() throws IOException {
        Random r = new Random();
        int max = 1000000;
        List<String> lines = IntStream.range(1, max)
                .boxed()
                .map(i -> String.valueOf(r.nextInt(max)))
                .collect(Collectors.toList());
        writeFile("secondLargest_1", lines);
    }

    public static void main(String[] args) throws IOException {
        generateBigInputFile();
    }
}
