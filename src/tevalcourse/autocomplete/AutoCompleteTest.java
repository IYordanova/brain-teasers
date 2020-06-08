package tevalcourse.autocomplete;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoCompleteTest extends BaseTestHelper {

    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>();

//        // empty dict
//        words = Collections.emptyList();
//        new AutoCompleter(words).solve(Collections.singletonList("asd"));
//        System.out.println(SEPARATOR);
//
//        // empty request
//        words = Collections.singletonList("asd");
//        new AutoCompleter(words).solve(Collections.emptyList());
//        System.out.println(SEPARATOR);
//
//        // basic examples
//        words = Arrays.asList("tech", "TECH", "computer", "technology", "elevate", "compute", "elevator", "company", "view");
//        new AutoCompleter(words).solve(Arrays.asList("tevh", "new", "techn", "compa", "elevato", "bie"));
//        System.out.println(SEPARATOR);

        // many alt typos
//        words = readFile("dummyWords_1");
//        new AutoCompleter(words).solve(Arrays.asList(
//                "tec"
//        ));
//        System.out.println(SEPARATOR);

//        // regular dictionary
//        words = readFile("words");
//        new AutoCompleter(words).solve(Arrays.asList("acc", "woman,", "a", "", "acc", "wripe"));

        words = readFile("dummyWords_2");
        new AutoCompleter(words).solve(Arrays.asList("tech"));
    }


    private static void createBigTestFile() throws IOException {
        String word = "tech";
        List<String> wordLines = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            if (i % 2 != 0) {
                wordLines.add(word + "e".repeat(i/2));
            } else if (i % 3 != 0) {
                wordLines.add(word + "n".repeat(i/3));
            } else if (i % 5 == 0) {
                wordLines.add(word + "h".repeat(i/5));
            } else if (i % 7 == 0) {
                wordLines.add(word + "y".repeat(i/7));
            } else {
                wordLines.add(word + "p".repeat(i));
            }
        }
        writeFile("dummyWords_2", wordLines);
    }

}
