package tevalcourse.seachautocomplete;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class AutoCompleter {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final ExecutorService executor;
    private final Trie trie;

    public AutoCompleter(List<String> dict) {
        this.trie = new Trie(dict);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void solve(List<String> queries) {
        for (String q : queries) {
            String query = q.toLowerCase();
            Set<String> candidates = findCandidates(trie, query);
            if (candidates.size() < limit && q.length() > 1) {
                candidates.addAll(getAlternativeCandidates(query, candidates));
            }
            if (candidates.isEmpty()) {
                System.out.println(NO_MATCHES_MESSAGE);
            }
            StringBuilder result = new StringBuilder();
            for (String candidate : candidates) {
                result.append(candidate).append(" ");
            }
            System.out.println(result.toString().trim());
        }
        executor.shutdown();
    }

    private Set<String> getAlternativeCandidates(final String query, Set<String> result) {
        if (result.size() < 10) {
            Future<Set<String>> candidateOptions = executor.submit(new Callable<Set<String>>() {
                @Override
                public Set<String> call() throws Exception {
                    return getAltCandidates(query);
                }
            });
            try {
                result.addAll(candidateOptions.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            }
        }
        return result;
    }

    private Set<String> getAltCandidates(String query) {
        Set<String> altQueries = spellChecker.typos(query);
        Set<String> altCandidates = new HashSet<>();
        for (String altQuery : altQueries) {
            altCandidates.addAll(findCandidates(trie, altQuery));
        }
        return altCandidates;
    }

    private Set<String> findCandidates(Trie trie, String query) {
        Node foundNode = trie.locateNode(trie.root, query);
        return trie.getCandidates(foundNode, limit);
    }
}
