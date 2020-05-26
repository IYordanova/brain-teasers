package brainteasers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class NGram {

    private static final String NORMALIZE_PATTERN = "[[^a-zA-Z0-9']+|\\s]";

    private static final String TEXT = "Mary had a little lamb its fleece was white as snow;"
            + "And everywhere that Mary went, the lamb was sure to go."
            + "It followed her to school one day, which was against the rule;"
            + "It made the children laugh and play, to see a lamb at school."
            + "And so the teacher turned it out, but still it lingered near,"
            + "And waited patiently about till Mary did appear."
            + "\"Why does the lamb love Mary so?\" the eager children cry;"
            + "\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";

    private static final WordTree wordTree = new WordTree();

    static class WordTree {
        private final WordNode root;

        public WordTree() {
            this.root = new WordNode(null);
        }

        public void insert(String word, String immediateChild) {
            root.addChild(word, immediateChild);
        }

        public WordNode find(String word) {
            return root.getChild(word);
        }

        public Map<String, Double> getPredictions(String word, Integer nGramLen) {
            WordNode currentNode = root.getChild(word);
            Map<WordNode, Map.Entry<String, Double>> predictions = new HashMap<>();
            predictions.put(currentNode, Map.entry(currentNode.content, 1.0));
            calcProbability(currentNode, predictions, 1, nGramLen);
            return predictions.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            nodeWordProbEntry -> nodeWordProbEntry.getValue().getKey(),
                            nodeWordProbEntry -> nodeWordProbEntry.getValue().getValue()
                    ));
        }

        private void calcProbability(
                WordNode currentNode,
                Map<WordNode, Map.Entry<String, Double>> predictions,
                int nGramLen,
                int targetNGramLen) {
            if (targetNGramLen == nGramLen) {
                return;
            }
            Map.Entry<String, Double> tempPredictions = predictions.get(currentNode);
            currentNode.getChildren()
                    .forEach((childWord, childNode) -> {
                        predictions.put(childNode, Map.entry(
                                tempPredictions.getKey() + " " + childWord,
                                tempPredictions.getValue() * (double) currentNode.getChildOccurrences(childWord) / (double) currentNode.getNumOccurrences())
                        );
                        calcProbability(childNode, predictions, nGramLen + 1, targetNGramLen);
                    });
        }

        static class WordNode {
            private final String content;
            private int numOccurrences = 0;
            private HashMap<String, WordNode> children = new HashMap<>();
            private HashMap<String, Integer> childrenOccurrences = new HashMap<>();

            public WordNode(String content) {
                this.content = content;
            }

            void addChild(String word, String immediateChild) {
                if (children.containsKey(word)) {
                    WordNode wordNode = this.children.get(word);
                    wordNode.incrementOccurrences();
                    this.childrenOccurrences.computeIfPresent(word, (wordKey, oldNum) -> oldNum + 1);
                    wordNode.addChild(immediateChild);
                } else {
                    WordNode wordNode = new WordNode(word);
                    wordNode.incrementOccurrences();
                    wordNode.addChild(immediateChild);
                    this.children.put(word, wordNode);
                }
            }

            private void addChild(String word) {
                this.children.computeIfAbsent(word, wordKey -> new WordNode(word));
                this.childrenOccurrences.computeIfPresent(word, (wordKey, oldNum) -> oldNum + 1);
                this.childrenOccurrences.computeIfAbsent(word, (wordKey) -> 1);
            }

            private void incrementOccurrences() {
                this.numOccurrences++;
            }

            public WordNode getChild(String word) {
                return this.children.get(word);
            }

            public Map<String, WordNode> getChildren() {
                return new HashMap<>(children);
            }

            public int getChildOccurrences(String child) {
                return this.childrenOccurrences.get(child);
            }


            public String getContent() {
                return this.content;
            }

            public int getNumOccurrences() {
                return this.numOccurrences;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                WordNode wordNode = (WordNode) o;
                return Objects.equals(content, wordNode.content);
            }

            @Override
            public int hashCode() {
                return Objects.hash(content);
            }
        }
    }

    static {
        String[] words = Arrays.stream(TEXT.split(NORMALIZE_PATTERN))
                .filter(word -> !word.isBlank())
                .toArray(String[]::new);
        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            String immediateChild = words[i + 1];
            wordTree.insert(word, immediateChild);
        }
    }


    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            String[] input = line.split(",");
            if (input.length < 2) {
                continue;
            }
            String nGramLen = input[0];
            String word = input[1];
            String result = wordTree.getPredictions(word, Integer.parseInt(nGramLen))
                    .entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "," + entry.getValue()) //TODO: format double
                    .collect(Collectors.joining(";"));
            System.out.println(result);
        }
    }

}
