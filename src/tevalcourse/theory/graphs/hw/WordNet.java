package tevalcourse.theory.graphs.hw;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private final Digraph digraph;
    private final Map<String, Set<Integer>> nouns;
    private final Map<Integer, String> synsets;
    private final SAP sap;


    public WordNet(String synsetsFile, String hypernymsFile) {
        if (synsetsFile == null || hypernymsFile == null) {
            throw new IllegalArgumentException("Input file names cannot be null");
        }

        this.nouns = new HashMap<>();
        this.synsets = new HashMap<>();
        int numVertices = readSynsets(synsetsFile);

        this.digraph = new Digraph(numVertices);
        readHypernyms(hypernymsFile);

        this.sap = new SAP(digraph);
        validateDag();
    }

    private void validateDag() {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException("Graph is not acyclic.");
        }
    }

    private void readHypernyms(String hypernyms) {
        In in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] vertices = in.readLine().split(",");
            int v = Integer.parseInt(vertices[0]);
            for (int i = 1; i < vertices.length; i++) {
                digraph.addEdge(v, Integer.parseInt(vertices[i]));
            }
        }
    }

    private int readSynsets(String synsetsFile) {
        In in = new In(synsetsFile);
        while (in.hasNextLine()) {
            String[] idSynsetGlossTriple = in.readLine().split(",");
            int id = Integer.parseInt(idSynsetGlossTriple[0]);
            String synset = idSynsetGlossTriple[1];

            synsets.put(id, synset);

            String[] nounsInSynset = synset.split("\\s+");
            for (String noun : nounsInSynset) {
                nouns.putIfAbsent(noun, new HashSet<>());
                nouns.get(noun).add(id);
            }
        }
        return nouns.size();
    }

    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    public boolean isNoun(String word) {
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        validateNouns(nounA, nounB);
        return sap.length(nouns.get(nounA), nouns.get(nounB));
    }

    public String sap(String nounA, String nounB) {
        validateNouns(nounA, nounB);
        int closestAncestor = sap.ancestor(nouns.get(nounA), nouns.get(nounB));
        return synsets.get(closestAncestor);
    }

    private void validateNouns(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);

        System.out.println(wordNet.distance("lomustine", "crosscut"));
        System.out.println(wordNet.distance("low-density_lipoprotein", "oil"));
        System.out.println(wordNet.distance("ossicle", "safflower_oil"));

        System.out.println(wordNet.sap("suicide", "Caesar_salad"));

    }
}
