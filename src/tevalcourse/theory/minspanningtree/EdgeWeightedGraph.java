package tevalcourse.theory.minspanningtree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    private final int V;
    private final Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }


    public EdgeWeightedGraph(In in) {
        String[] strings = in.readAllLines();
        this.V = strings.length;
        this.adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        for (String str : strings) {
            String[] ints = str.split("-");
            int v = Integer.parseInt(ints[0]);
            int w = Integer.parseInt(ints[1]);
            double weight = Double.parseDouble(ints[1]);
            addEdge(new Edge(v, w, weight));
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges(){
        // TODO:
        return null;
    }

    public int v() {
        return V;
    }

    public int e() {
        int count = 0;
        for (int v = 0; v < V; v++) {
            for (Edge w : adj(v)) {
                count++;
            }
        }
        return count;
    }
}
