package tevalcourse.theory.graphs;


import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
  - the edges have direction
  - applications:
        - routes on a map
        - code analysis
        - garbage collection

  - DAG - directed acyclic graph
        - topological sort - all edges to point upwards

  - strongly connected v and w - if direct path both ways
 */
public class DirectedGraph implements Graph {

    private final int V;
    private Bag<Integer>[] adj;


    public DirectedGraph(int V) {
        this.V = V;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }


    public DirectedGraph(In in) {
        String[] strings = in.readAllLines();
        this.V = strings.length;
        this.adj = (Bag<Integer>[]) new Bag[V];
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

    public DirectedGraph reverse() {
        //TODO:
        return null;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        DirectedGraph graph = new DirectedGraph(in);
        for (int v = 0; v < graph.v(); v++) {
            for (int w : graph.adj(v)) {
                StdOut.println(v + " -> " + w);
            }
        }
    }
}
