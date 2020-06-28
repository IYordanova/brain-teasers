package tevalcourse.theory.graphs.shortestpaths;


import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

/*
  - acyclic shortest path
  - relax all edges pointing from each vertex in a topological order
  - applications - seam carving - image resizing

  - to get longest path - negate the weights and run the same algorithm
  - applications - job scheduling problem - 2 vertices start/source and end/sink,
    the edges are the durations of each job and the end vertices are the jobs depending on the prev to start
    the schedule is the longest path in the resulting DAG
 */
public class EdgeWeightedGraphsSP {
    private final DirectedEdge[] edgeTo;
    private final double[] distTo;

    public EdgeWeightedGraphsSP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.V()];
        distTo = new double[digraph.V()];

        for(int v = 0; v < digraph.V(); v ++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topological = new Topological(digraph);
        for(int v : topological.order()) {
            for(DirectedEdge e : digraph.adj(v)) {
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
        }
    }
}
