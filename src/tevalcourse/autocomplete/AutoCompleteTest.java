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

        // empty dict
        words = Collections.emptyList();
        new AutoCompleter(words).solve(Collections.singletonList("asd"));
        System.out.println(SEPARATOR);

        // empty request
        words = Collections.singletonList("asd");
        new AutoCompleter(words).solve(Collections.emptyList());
        System.out.println(SEPARATOR);

        // basic examples
        words = Arrays.asList("algo", "alter", "data", "datum", "dest", "world", "could", "would");
        new AutoCompleter(words).solve(Arrays.asList("blah", "algo", "a", "vorl", "would"));
        System.out.println(SEPARATOR);

        words = Arrays.asList("tech", "TECH", "computer", "technology", "elevate", "compute", "elevator", "company", "view");
        new AutoCompleter(words).solve(Arrays.asList("tevh", "new", "techn", "compa", "elevato", "bie"));
        System.out.println(SEPARATOR);

        // many alt typos
        words = readFile("dummyWords");
        new AutoCompleter(words).solve(Arrays.asList(
                "sm",
                "smm" ,
                "smol",
                "amal",
                "smockingj",
                "smu",
                "TH",
                "te",
                "smag"
        ));
        System.out.println(SEPARATOR);

        // regular dictionary
        words = readFile("words");
        new AutoCompleter(words).solve(Arrays.asList("acc", "woman,", "a", "", "acc"));
    }


}
