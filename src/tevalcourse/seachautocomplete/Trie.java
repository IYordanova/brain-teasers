package tevalcourse.seachautocomplete;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    public Node locateNode(Node root, String prefix) {
        Node result = root;
        for (char c : prefix.toCharArray()) {
            result = result.getChild(c);
            if (result == null) {
                break;
            }
        }
        return result;
    }

    public List<String> getCandidates(Node node, int limit) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty() && result.size() < limit) {
            Node n = queue.pollFirst();
            String word = n.getWord();
            if (word != null) {
                result.add(word);
            }
            queue.addAll(n.getChildNodes());
        }
        return result;
    }
}
