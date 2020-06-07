package tevalcourse.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        solveHelper(queries, new ArrayList<>(), 0).forEach(System.out::println);
    }

    private List<String> solveHelper(List<String> queries, List<String> result, int i) {
        if (i == queries.size()) {
            return result;
        }
        String query = queries.get(i).toLowerCase();
        Set<String> candidates = trie.find(query);
        spellChecker.typos(query).forEach(altQuery -> candidates.addAll(trie.find(altQuery)));
        result.add(candidates.isEmpty()
                ? NO_MATCHES_MESSAGE
                : candidates.stream()
                .sorted()
                .sequential()
                .limit(LIMIT)
                .collect(Collectors.joining(" ")));
        return solveHelper(queries, result, ++i);
    }

}
