package tevalcourse.theory.graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
  - graph - set of vertices connected by edges
  - path - sequence of vertices connected by edges
  - cycle - path whose first and last vertices are the same
  - connected vertices - if there is a path between them
  - number of edges connected to vertex v

  - Euler tour - cycle that uses each edge exactly once
  - Hamilton tour - cycle that uses each vertex exactly once
  - connectivity - is there a way to connect all vertices
  - MST - best way to connect all vertices
  - Biconnectivity - is there a vertex whose removal disconnects the graph
  - Planarity - can you draw the graph in the plane with no crossing edges
  - Graph isomorphism - do 2 adjacency lists represent the same graph

  Possible representations:
  - linked list for every edge - inefficient
  - 2d matrix VxV and for every edge add true in the col and row - inefficient and too much space
  - adjacency list representation
    - for every vertex -> list of connected ones
    - vertices in array
 */
public class UndirectedGraph implements Graph {

    private final int V;
    private Bag<Integer>[] adj;

    public UndirectedGraph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public UndirectedGraph(In in) {
        String[] strings = in.readAllLines();
        this.V = strings.length;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        for (String str : strings) {
            String[] ints = str.split("-");
            int v = Integer.parseInt(ints[0]);
            int w = Integer.parseInt(ints[1]);
            addEdge(v, w);
        }
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public int v() {
        return V;
    }

    @Override
    public int e() {
        int count = 0;
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        UndirectedGraph graph = new UndirectedGraph(in);
        for (int v = 0; v < graph.v(); v++) {
            for (int w : graph.adj(v)) {
                StdOut.println(v + " " + w);
            }
        }
    }

}
