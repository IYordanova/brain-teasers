package tevalcourse.autocomplete;

import tevalcourse.BaseTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTest extends BaseTest {

    private static class TestCase {
        final List<String> words;
        final List<String> queries;

        public TestCase(List<String> words, List<String> queries) {
            this.words = words;
            this.queries = queries;
        }
    }

    private static TestCase prepareTestCase(String fileName) throws IOException {
        List<String> lines = readFile(fileName);
        TestCase testCase = new TestCase(new ArrayList<>(), new ArrayList<>());

        int numWords = Integer.parseInt(lines.get(0));
        testCase.words.addAll(lines.subList(1, numWords + 1));
        testCase.queries.addAll(lines.subList(numWords + 2, lines.size()));

        return testCase;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        TestCase testCase2 = prepareTestCase("autocomplete_1");
        new AutoCompleter(testCase2.words).solve(testCase2.queries);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
