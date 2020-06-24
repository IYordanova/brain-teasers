package tevalcourse.theory.graphs.hw;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) {
        String outcast = nouns[0];
        int distanceSum = distanceSum(nouns[0], nouns);
        for (int i = 1; i < nouns.length; i++) {
            int tempdist = distanceSum(nouns[i], nouns);
            if (distanceSum < tempdist) {
                distanceSum = tempdist;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    private int distanceSum(String noun, String[] nouns) {
        int distanceSum = 0;
        for (String n : nouns) {
            distanceSum += wordNet.distance(noun, n);
        }
        return distanceSum;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
