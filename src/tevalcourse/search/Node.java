package tevalcourse.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private final List<String> titles;
    private final Map<String, Node> children;

    public Node() {
        this.titles = new ArrayList<>();
        this.children = new HashMap<>();
    }

    public Node(List<String> titles, Map<String, Node> children) {
        this.titles = titles;
        this.children = children;
    }

    public List<String> getTitles() {
        return titles;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return String.format("Node{titles=%s, children=%s}", titles, children);
    }
}
