package tevalcourse.autocomplete;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private String word;
    private final Map<Character, Node> children;

    public Node() {
        this.word = null;
        this.children =  new HashMap<>();
    }

    public Node(String word, Map<Character, Node> children) {
        this.word = word;
        this.children = children;
    }

    public Node getChild(char c) {
        if (!children.containsKey(c)) {
            children.put(c, new Node());
        }
        return children.get(c);
    }

    public Collection<Node> getChildNodes() {
        return children.values();
    }

    public String getWord() {
        return word;
    }

    public void updateWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return String.format("Node{word='%s', children=%s}", word, children);
    }
}
