package tevalcourse.autocomplete;


import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
import java.util.TreeSet;


public class Trie {
    final Node root;

    public Trie(List<String> words) {
        this.root = new Node();
        for (String word : words) {
            insertWord(root, word);
        }
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

    private TreeSet<String> getCandidates(Node node, int limit) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        return findCandidates(queue, new TreeSet<>(), limit);
    }

    private TreeSet<String> findCandidates(Deque<Node> queue, TreeSet<String> result, int limit) {
        if (queue.isEmpty() || result.size() == limit) {
            return result;
        }
        Node node = queue.pollFirst();
        String word = node.getWord();
        if (word != null) {
            result.add(word);
        }
        queue.addAll(node.getChildNodes());
        return findCandidates(queue, result, limit);
    }

    public TreeSet<String> find(String query, int limit) {
        Node node = locateNode(root, query);
        return getCandidates(node, limit);
    }
}