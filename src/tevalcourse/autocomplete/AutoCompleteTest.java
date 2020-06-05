package tevalcourse.autocomplete;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoCompleteTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("algo", "alter", "data", "datum", "dest");
        new AutoCompleter(words).solve(Arrays.asList("blah", "algo", "a"));
        System.out.println();

        words = Arrays.asList("tech", "TECH", "computer", "technology", "elevate", "compute", "elevator", "company", "view");
        new AutoCompleter(words).solve(Arrays.asList("tevh", "new", "techn", "compa", "elevato", "bie"));
        System.out.println();

        words = Arrays.asList(
                "smo", "sma", "smi",
                "smp", "smd", "smr",
                "smh", "smf", "smw",
                "smq", "smalpp", "smalooo",
                "smogsadboafbkedscj", "smockingjsjaskjdak",
                "smack", "smuggle", "smiling",
                "SMUG", "smug", "smoke", "smirk", "the");
        new AutoCompleter(words).solve(Arrays.asList("sm", "smm", "smol", "amal", "smockingj", "/", "smu", "TH", "te"));
        System.out.println();

        words = Collections.emptyList();
        new AutoCompleter(words).solve(Arrays.asList("sm", "smol", "snails"));
        System.out.println();

        words = Arrays.asList("sm", "smol", "snails");
        new AutoCompleter(words).solve(Collections.emptyList());
    }
}
