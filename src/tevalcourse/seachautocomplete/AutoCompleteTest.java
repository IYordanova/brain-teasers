package tevalcourse.seachautocomplete;

import java.util.Arrays;
import java.util.List;

public class AutoCompleteTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("algo", "alter", "data", "datum", "dest");
        new AutoCompleter(words).solve(Arrays.asList("blah", "algo", "a"));  // [null], [algo], [algo, alter]

        List<String> words2 = Arrays.asList("tech", "computer", "technology", "elevate", "compute", "elevator", "company");
        new AutoCompleter(words2).solve(Arrays.asList("tevh", "new", "techn", "compa"));
    }
}
