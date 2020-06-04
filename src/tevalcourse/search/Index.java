package tevalcourse.search;


import java.util.*;
import java.util.stream.Collectors;

public class Index {
    private final Node root;

    public Index(List<String> pages) {
        this.root = new Node();
        init(pages);
    }

    private void init(List<String> pages) {
        for (String pageTitle : pages) {
            int i = 0;
            List<String> words = extractWords(pageTitle);
            while (i < words.size()) {
                Node current = root;
                for (String word : words) {
                    current.getChildren().putIfAbsent(word, new Node());
                    current = current.getChildren().get(word);
                }
                i++;
                current.getTitles().add(pageTitle);
            }
        }
    }

    List<String> extractWords(String page) {
        return Arrays.stream(page.split("\\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    Node getRoot() {
        return root;
    }

    private Set<String> findCandidates(Node node, int limit) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        Set<String> result = new TreeSet<>();
        while (!queue.isEmpty() && result.size() < limit) {
            Node n = queue.pollFirst();
            List<String> word = n.getTitles();
            if (word != null) {
                result.addAll(word);
            }
            queue.addAll(n.getChildren().values());
        }
        return result;
    }

    private Node findNode(Node index, List<String> keywords) {
        Node node = index;
        for (String keyword : keywords) {
            node = node.getChildren().get(keyword);
            if (node != null) {
                break;
            }
        }
        return node;
    }

    public Set<String> find(String query, int limit) {
        Node node = findNode(root, extractWords(query));
        return findCandidates(node, limit);
    }
}
