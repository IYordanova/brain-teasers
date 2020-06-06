package tevalcourse.autocomplete;

import tevalcourse.BaseTestHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoCompleteTest extends BaseTestHelper {

    public static void main(String[] args) throws IOException {
        // empty dict
        List<String> words = Collections.emptyList();
        new AutoCompleter(words).solve(Collections.singletonList("asd"));
        System.out.println();

        // empty request
        words = Collections.singletonList("asd");
        new AutoCompleter(words).solve(Collections.emptyList());

        // basic examples
        words = Arrays.asList("algo", "alter", "data", "datum", "dest", "world", "could", "would");
        new AutoCompleter(words).solve(Arrays.asList("blah", "algo", "a", "vorl", "would"));
        System.out.println();

        words = Arrays.asList("tech", "TECH", "computer", "technology", "elevate", "compute", "elevator", "company", "view");
        new AutoCompleter(words).solve(Arrays.asList("tevh", "new", "techn", "compa", "elevato", "bie"));
        System.out.println();

        // many alt typos
        words = Arrays.asList(
                "smo", "sma", "smi",
                "smp", "smd", "smr",
                "smh", "smf", "smw",
                "smq", "smalpp", "smalpp", "smalpp", "smalpp", "smalpp", "smalpp", "smalooo",
                "smogsadboafbkedscj", "smockingjsjaskjdak",
                "smack", "smuggle", "smiling",
                "SMUG", "smug", "smoke", "smirk", "the");
        new AutoCompleter(words).solve(Arrays.asList(
                "sm", "smm", "smol", "amal",
                "smockingj", "/", "smu", "TH", "te",
                "smag"));
        System.out.println();

        // regular dictionary
        words = readFile("words");
        new AutoCompleter(words).solve(Arrays.asList("acc", "woman,", "a", "", "acc"));
    }
}
