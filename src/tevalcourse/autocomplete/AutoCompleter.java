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
        List<List<String>> allQueries = queries.stream()
                .sequential()
                .map(query -> {
                    List<String> altQueries = new ArrayList<>(spellChecker.typos(query));
                    altQueries.add(query);
                    return altQueries;
                })
                .collect(Collectors.toList());

        for(List<String> query: allQueries) {
            Set<String> result = solveHelper(query.iterator(), new HashSet<>());
            if (result.isEmpty()) {
                System.out.println(NO_MATCHES_MESSAGE);
            } else {
                System.out.println(result.stream()
                        .sorted()
                        .sequential()
                        .limit(LIMIT)
                        .collect(Collectors.joining(" ")) + " ");
            }
        }
    }

    private Set<String> solveHelper(Iterator<String> queries, Set<String> result) {
        if (!queries.hasNext() || result.size() >= LIMIT) {
            return result;
        }
        String query = queries.next().toLowerCase().trim();
        if (query.isBlank()) {
            result.add(NO_MATCHES_MESSAGE);
            return solveHelper(queries, result);
        }
        result.addAll(trie.find(query));
        return solveHelper(queries, result);
    }

}
