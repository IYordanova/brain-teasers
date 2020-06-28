package tevalcourse.theory.graphs.shortestpaths;

import edu.princeton.cs.algs4.*;


/*
   - Dijkstra doesn't work
   - expect no negative cycle - sum of weights is negative
      -  application - exchange rates - whether by conversion can make money - arbitrage
      - vertex fo every currency
      - weight on the edge the conversion rate
      - find cycle with negative sum
   -  Bellman-Ford Algorithm - for each vertex go over each edge, relax the edges
      in N passes so each time around the corrections will find the shorter paths
      - in practice - maintain a queue of the vertices for which you've found a shorter path -
        no need tto worry about the others
      - EV time
 */
public class NegativeWeightSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;


    public NegativeWeightSP(EdgeWeightedDigraph G, int s) {

    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }


    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = 0;

        NegativeWeightSP sp = new NegativeWeightSP(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%d to %d (%.2f): ", s, v, sp.distTo(v));
            for (DirectedEdge edge : sp.pathTo(v)) {
                StdOut.print(edge + " ");
            }
            StdOut.println();
        }
    }
}
