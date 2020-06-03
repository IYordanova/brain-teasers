package tevalcourse.seachautocomplete;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AutoComplete {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final ExecutorService executor;
    private final Trie trie;

    public AutoComplete(List<String> dict) {
        this.trie = new Trie(dict);
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;
    }

    public void solve(List<String> queries) {
        for (String query : queries) {
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
            if(candidates.size() < limit) {
                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::missingCharAtStart));
            }
            if(candidates.size() < limit) {
                candidates.addAll(getAlternativeCandidates(query, candidates, spellChecker::additionalCharAtStart));
            }
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
