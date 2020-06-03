package tevalcourse.seachautocomplete;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Node {
    private String word;
    private final Map<Character, Node> children;

    public Node() {
        this(null, new HashMap<>());
    }

    public Node(String word, Map<Character, Node> children) {
        this.word = word;
        this.children = children;
    }

    public Node getChild(char c) {
        children.putIfAbsent(c, new Node());
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
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("word='" + word + "'")
                .add("children=" + children)
                .toString();
    }
}
