package tevalcourse.seachautocomplete;

import java.util.*;

public class AutoComplete {

    private static final int limit = 10;
    private static final String NO_MATCHES_MESSAGE = "<no matches>";

    public static void main(String[] args) {
        List<String> words = Arrays.asList("algo", "alter", "data", "datum", "dest");
        solve(words, Arrays.asList("blah", "algo", "a"));  // [null], [algo], [algo, alter]
    }

    static private void solve(List<String> dict, List<String> queries) {
        Trie trie = new Trie(dict);
        for(String query : queries) {
            Node foundNode = trie.locateNode(trie.root, query);
            Set<String> candidates = trie.getCandidates(foundNode, limit);
            System.out.println(candidates.isEmpty() ? NO_MATCHES_MESSAGE : String.join(" ", candidates));
        }
    }
}
