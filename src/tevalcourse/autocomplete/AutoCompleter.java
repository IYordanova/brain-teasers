package tevalcourse.autocomplete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoCompleter {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final Trie trie;

    public AutoCompleter(List<String> dict) {
        this.trie = new Trie(dict);
    }

    public void solve(List<String> queries) {
        solveHelper(queries, new ArrayList<>()).forEach(System.out::println);
    }

    private List<String> solveHelper(List<String> queries, List<String> result) {
        if (queries.isEmpty()) {
            return result;
        }
        String query = queries.get(0).toLowerCase();
        Set<String> candidates = trie.find(query, limit);
        if (candidates.size() < limit) {
            spellChecker.typos(query).forEach(altQuery -> candidates.addAll(trie.find(altQuery, limit)));
        }
        result.add(candidates.isEmpty()
                ? NO_MATCHES_MESSAGE
                : candidates.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(String::trim))
                .sequential()
                .limit(limit)
                .collect(Collectors.joining(" ")));
        return solveHelper(tail(queries), result);
    }

    private List<String> tail(List<String> queries) {
        return queries.subList(1, queries.size());
    }

}
