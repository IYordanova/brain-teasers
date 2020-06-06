package tevalcourse.search;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;
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
                    current = current.getChildren().get(nextWord);
                }
                current.getTitles().add(page);
            }
        }
    }

    private List<String> extractWords(String page) {
        return Arrays.stream(page.split("\\s+"))
                .map(String::toLowerCase)
                .sorted()
                .sequential()
                .collect(Collectors.toList());
    }

    private List<String> findCandidates(Deque<Node> queue, int limit, List<String> result, List<String> keywords) {
        if (queue.isEmpty() || result.size() >= limit) {
            return result.stream().limit(limit).collect(Collectors.toList());
        } else {
            Node nextNode = queue.pollFirst();
            List<String> titles = nextNode.getTitles();
            if (titles != null && !titles.isEmpty()) {
                result.addAll(titles
                        .stream()
                        .filter(title -> Arrays.asList(title.split("\\s+")).containsAll(keywords))
                        .collect(Collectors.toList()));
            }
            queue.addAll(nextNode.getChildren().values());
            return findCandidates(queue, limit, result, keywords);
        }
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

    public List<String> find(String query, int limit) {
        List<String> keywords = extractWords(query);
        Node node = findNode(root, keywords);
        if (node == root) {
            return Collections.emptyList();
        }
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        return findCandidates(queue, limit, new ArrayList<>(), keywords);
    }
}
