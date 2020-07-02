package tevalcourse.autocomplete;

import java.util.*;


public class Trie {
    private final Node root;

    public Trie(List<String> words) {
        this.root = new Node();
        words.forEach(word -> insertWord(root, word.toLowerCase().trim()));
    }

    private void insertWord(Node root, String word) {
        Node currentNode = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            currentNode = currentNode.getChild(c);
        }
        currentNode.updateWord(word);
    }

    private Node locateNode(Node root, String prefix) {
        Node current = root;
        char[] chars = prefix.toCharArray();
        for (char ch : chars) {
            Node node = current.getChild(ch);
            if (node == null) {
                break;
            }
            current = node;
        }
        return current;
    }

    private Set<String> getCandidates(Node node) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        Set<String> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            String word = currentNode.getWord();
            if (word != null) {
                result.add(word);
            }
            queue.addAll(currentNode.getChildNodes());
        }
        return result;
    }

    public Set<String> find(String query) {
        Node node = locateNode(root, query);
        if (node.getWord() == null && (node.getChildNodes() == null || node.getChildNodes().isEmpty())) {
            return Collections.emptySet();
        }
        return getCandidates(node);
    }

}