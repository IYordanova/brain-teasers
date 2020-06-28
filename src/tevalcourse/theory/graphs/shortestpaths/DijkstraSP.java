package tevalcourse.theory.graphs.shortestpaths;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;

public class DijkstraSP {
    private final DirectedEdge[] edgeTo;
    private final double[] distTo;
    private final IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        int vertices = graph.V();

        // init
        edgeTo = new DirectedEdge[vertices];
        distTo = new double[vertices];
        pq = new IndexMinPQ<>(vertices);

        for (int v = 0; v < vertices; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        // find shortest paths
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : graph.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
}
