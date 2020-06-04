package tevalcourse.autocomplete;

import java.util.Arrays;
import java.util.List;

public class AutoCompleteTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("algo", "alter", "data", "datum", "dest");
        new AutoCompleter(words).solve(Arrays.asList("blah", "algo", "a"));
        System.out.println();

        words = Arrays.asList("tech", "computer", "technology", "elevate", "compute", "elevator", "company");
        new AutoCompleter(words).solve(Arrays.asList("tevh", "new", "techn", "compa"));
        System.out.println();

        words = Arrays.asList(
                "smalljasbadsjk", "smile", "smaller",
                "smallest", "smalaksda", "smalasjdhvb",
                "smalaskldcnv", "smalaaa", "smalbbbb",
                "smaljf", "smalpp", "smalooo",
                "smogsadboafbkedscj", "smockingjsjaskjdak",
                "smack", "smuggle", "smiling",
                "smug", "smoke", "smirk");
        new AutoCompleter(words).solve(Arrays.asList("sm", "smol", "amal"));
        System.out.println();

//        words = Arrays.asList(
//                "smalljasbadsjk", "smile", "smaller",
//                "smallest", "smogsadboafbkedscj", "smockingjsjaskjdak",
//                "smack", "smuggle", "smiling",
//                "smug", "smoke", "smirk");
//        new AutoCompleter(words).solve(Arrays.asList("sm", "smol"));
    }
}
