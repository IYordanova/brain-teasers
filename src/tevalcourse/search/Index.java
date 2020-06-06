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
        for (String page : pages) {
            List<String> words = extractWords(page);
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                root.getChildren().putIfAbsent(word, new Node());
                Node current = root.getChildren().get(word);
                for (int j = i + 1; j < words.size(); j++) {
                    String nextWord = words.get(j);
                    current.getChildren().putIfAbsent(nextWord, new Node());
                    current.getTitles().add(page);
                    current = current.getChildren().get(nextWord);
                }
                current.getTitles().add(page);
            }
        }
    }

    List<String> extractWords(String page) {
        return Arrays.stream(page.split("\\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private Set<String> findCandidates(Node node, List<String> keywords, int limit) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        Set<String> result = new TreeSet<>();
        while (!queue.isEmpty() && result.size() < limit) {
            Node nextNode = queue.pollFirst();
            List<String> titles = nextNode.getTitles();
            if (titles != null) {
                titles.stream()
                    .filter(title -> Arrays.asList(title.split("\\s+")).containsAll(keywords))
                    .forEach(result::add);
            }
            queue.addAll(nextNode.getChildren().values());
        }
        return result;
    }

    private Node findNode(Node index, List<String> keywords) {
        Node node = index;
        for (String keyword : keywords) {
            Node next = node.getChildren().get(keyword);
            if (next != null) {
                node = next;
            }
        }
        return node;
    }

    public Set<String> find(String query, int limit) {
        List<String> keywords = extractWords(query);
        Node node = findNode(root, keywords);
        if (node == root) {
            return Collections.emptySet();
        }
        return findCandidates(node, keywords, limit);
    }
}
