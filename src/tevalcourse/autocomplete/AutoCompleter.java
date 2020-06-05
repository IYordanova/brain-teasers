package tevalcourse.autocomplete;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AutoCompleter {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";
    private static final VeryBasicSpellChecker spellChecker = new VeryBasicSpellChecker();

    private final Trie trie;

    public AutoCompleter(List<String> dict) {
        this.trie = new Trie(dict);
    }

    public void solve(List<String> queries) {
        if(queries.isEmpty()) {
            return;
        }
        for (String q : queries) {
            String query = q.toLowerCase();
            TreeSet<String> candidates = trie.find(query, limit);
            if (candidates.size() < limit && q.length() > 1) {
                int leftToLimit = limit - candidates.size();
                addAlternativeCandidates(query, candidates, leftToLimit);
            }
            System.out.println(candidates.isEmpty() ? NO_MATCHES_MESSAGE : String.join(" ", candidates));
        }
    }

    private void addAlternativeCandidates(final String query, Set<String> result, int leftToLimit) {
        Set<String> altQueries = spellChecker.typos(query);
        int newLimit = leftToLimit;
        for (String altQuery : altQueries) {
            Set<String> altCandidates = trie.find(altQuery, newLimit);
            result.addAll(altCandidates);
            newLimit -= altCandidates.size();
            if (newLimit <= 0) {
                break;
            }
        }
    }

}
