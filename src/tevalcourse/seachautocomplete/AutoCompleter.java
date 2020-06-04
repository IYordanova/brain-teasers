package tevalcourse.seachautocomplete;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AutoCompleter {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final ExecutorService executor;
    private final Trie trie;

    public AutoCompleter(List<String> dict) {
        this.trie = new Trie(dict);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;
    }

    public void solve(List<String> queries) {
        for (String q : queries) {
            String query = q.toLowerCase();
            Set<String> candidates = findCandidates(trie, query);
            if(candidates.size() < limit) {
                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::additionalCharAtEnd));
            }
            if(candidates.size() < limit) {
                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::typos));
            }
            if(candidates.size() < limit) {
                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::swappedSeqChars));
            }
//            if(candidates.size() < limit) {
//                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::missingCharAtStart));
//            }
//            if(candidates.size() < limit) {
//                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::additionalCharAtStart));
//            }
            System.out.println(candidates.isEmpty() ? NO_MATCHES_MESSAGE : String.join(" ", candidates));
        }
        executor.shutdown();
    }

    private Set<String> getAlternativeCandidates(
            String query,
            Set<String> result,
            Function<String, Set<String>> getAlternativeCandidates) {
        if (result.size() < 10) {
            Future<Set<String>> candidateOptions = executor.submit(() -> getAlternativeCandidates.apply(query)
                    .stream()
                    .flatMap(q -> findCandidates(trie, q).stream())
                    .collect(Collectors.toSet()));
            try {
                result.addAll(candidateOptions.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            }
        }
        return result;
    }

    private Set<String> findCandidates(Trie trie, String query) {
        Node foundNode = trie.locateNode(trie.root, query);
        return trie.getCandidates(foundNode, limit);
    }
}
