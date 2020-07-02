package tevalcourse.autocomplete;

import java.util.*;
import java.util.stream.Collectors;

public class AutoCompleter {

    private static final int LIMIT = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final Trie trie;

    public AutoCompleter(List<String> dict) {
        this.trie = new Trie(dict);
    }

    public void solve(List<String> queries) {
        if (queries.isEmpty()) {
            System.out.println(NO_MATCHES_MESSAGE);
            return;
        }

        for (String originalQuery : queries) {
            Set<String> altQueries = spellChecker.typos(originalQuery);
            int foundMatches = 0;
            for (String query : altQueries) {
                if (!query.isBlank()) {
                    foundMatches += solveHelper(query, LIMIT - foundMatches);
                    if (foundMatches >= LIMIT) {
                        break;
                    }
                }
            }
            if (foundMatches == 0) {
                System.out.println(NO_MATCHES_MESSAGE);
            } else {
                System.out.println();
            }
        }
    }

    private int solveHelper(String query, int left) {
        Set<String> c = trie.find(query);
        if (!c.isEmpty()) {
            System.out.print(c.stream()
                    .sorted()
                    .sequential()
                    .limit(left)
                    .collect(Collectors.joining(" ")) + " ");
        }
        return c.size();
    }

}
