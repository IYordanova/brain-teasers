package tevalcourse.autocomplete;

import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;


public class Trie {
    private final Node root;

    public Trie(List<String> words) {
        this.root = new Node();
        words.forEach(word -> insertWord(root, word.toLowerCase().trim()));
    }

    private void insertWord(Node root, String word) {
        Node currentNode = root;
        for (char c : word.toCharArray()) {
            currentNode = currentNode.getChild(c);
        }
        currentNode.updateWord(word);
    }

    private Node locateNode(Node root, String prefix) {
        Node result = root;
        for (char c : prefix.toCharArray()) {
            result = result.getChild(c);
            if (result == null) {
                break;
            }
        }
        return result;
    }

    public Set<String> find(String query) {
        Node node = locateNode(root, query);
        return getCandidates(node);
    }

    private Set<String> getCandidates(Node node) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        return findCandidates(queue, new HashSet<>());
    }

    private Set<String> findCandidates(Deque<Node> queue, Set<String> result) {
        if (queue.isEmpty() ) {
            return result;
        }
        Node node = queue.pollFirst();
        String word = node.getWord();
        if (word != null) {
            result.add(word);
        }
        queue.addAll(node.getChildNodes());
        return findCandidates(queue, result);
    }
}