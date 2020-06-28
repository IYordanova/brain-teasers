package tevalcourse.theory.graphs.shortestpaths;

import edu.princeton.cs.algs4.*;


/*
   - goal is to compute the shortest path tree
   - 2 arrays
        - distTo[v] - length of the shortest path from s to v
        - edgeTo[v] - the last edge on the shortest path from s to v

   - edge relaxation
        - when considering a new vertex consider if that gives a shorter path
        -  if e = v->w is a shorter path to w through v
        - then update both distTo[w] and edgeTo[w]

   - optimality conditions
        - distTo[] are the shortest path distances from s if
            - for every vertex v, distTo[v] is the len of some path from s to v
            - for each edge e=v->w dist[w] <= dist[v] + e.weight()
 */
public class SP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;


    public SP(EdgeWeightedDigraph G, int s) {

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

        SP sp = new SP(G, s);
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%d to %d (%.2f): ", s, v, sp.distTo(v));
            for (DirectedEdge edge : sp.pathTo(v)) {
                StdOut.print(edge + " ");
            }
            StdOut.println();
        }
    }
}
