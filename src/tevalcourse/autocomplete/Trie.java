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
        char[] chars = word.toCharArray();
        for (char c : chars) {
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

    private Set<String> getCandidates(Node node, int limit) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        Set<String> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Node currentNode = queue.pollFirst();
            String word = currentNode.getWord();
            if (word != null) {
                result.add(word);
                if(result.size() >= limit) {
                    break;
                }
            }
            queue.addAll(currentNode.getChildNodes());
        }
        return result;
    }

    public Set<String> find(String query, int limit) {
        Node node = locateNode(root, query);
        return getCandidates(node, limit);
    }

}