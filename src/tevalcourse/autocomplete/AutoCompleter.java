package tevalcourse.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
            int leftToLimit = limit - candidates.size();
            addAlternativeCandidates(query, candidates);
        }
        result.add(candidates.isEmpty() ? NO_MATCHES_MESSAGE : String.join(" ", candidates.stream().limit(limit).collect(Collectors.toSet()))
        );
        return solveHelper(tail(queries), result);
    }

    private List<String> tail(List<String> queries) {
        return queries.subList(1, queries.size());
    }

    private void addAlternativeCandidates(final String query, Set<String> result) {
        Set<String> altQueries = spellChecker.typos(query);
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (String altQuery : altQueries) {
            Set<String> altCandidates = trie.find(altQuery, limit);
            result.addAll(altCandidates);
        }
//        executor.invokeAll()
    }

}
