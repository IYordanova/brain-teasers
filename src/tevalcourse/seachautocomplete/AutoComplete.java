package tevalcourse.seachautocomplete;

import java.util.*;

public class AutoComplete {

    public static void main(String[] args) {
        int limit = 10;
        List<String> words = Arrays.asList("algo", "alter", "data", "datum", "dest");
        Trie trie = new Trie(words);

        System.out.println(trie.locateNode(trie.root, "blah"));  // null
        System.out.println(trie.getCandidates(trie.locateNode(trie.root, "algo"), 10)); // algo
        System.out.println(trie.getCandidates(trie.locateNode(trie.root, "a"), 10)); // alter, algo
    }
}
