package tevalcourse.theory.graphs;

public class GraphStats {

    public static int degree(Graph graph, int v) {
        int degree = 0;
        for (int w : graph.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.v(); v++) {
            int degree = degree(graph, v);
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }

    public static double averageDegree(Graph graph) {
        return 2.0 * graph.e() / graph.v();
    }

    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.v(); v++) {
            for (int w : graph.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count;
    }

}
