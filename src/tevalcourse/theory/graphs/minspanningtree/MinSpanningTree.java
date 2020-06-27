package tevalcourse.theory.graphs.minspanningtree;


import edu.princeton.cs.algs4.*;

/*
   - subgraph of an undirected graph
   - with weighted edges
   - connected and acyclic
   - includes all vertices

   - cut in a graph - partition the vertices in 2 non-empty set
   - crossing edge - connects a vertex from one set to a vertex from the other
   - min weight crossing edge has to be in the MST

   Implementations:
   - Greedy algorithm:
        - start all in one set - all grey
        - go over vertices with no black edges and color the min
        - do until no more vertices with no black edges
        - implementations/ variations of the greedy alg - Kruskal's, Prim's and Boruvka's algorithms
    - Kruskal's Algorithm:
        - sort edges by weight
        - start adding them to MST unless hey create a cycle
        - time ElogE - works for huge graphs
    - Prim's Algorithm:
        - lazy version:
            - start with  v0 amd greedily grow t
            - add to t the min weight edge with exactly one endpoint
            - repeat until V-1 edges
        - eager version:
            - need a MinPQ allowing to decrease a priority of the key
            - once an edge to a vertex is added - if there is already a better path in the mst
                do not add the once from that vertex
 */
public class MinSpanningTree {

    private final Queue<Edge> kruskalMst = new Queue<>();

    private final Queue<Edge> primMst = new Queue<>();

    public MinSpanningTree(EdgeWeightedGraph g) {

    }

    private void kruskalAlg(EdgeWeightedGraph g) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : g.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(g.v());
        while (!pq.isEmpty() && kruskalMst.size() < g.v() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                kruskalMst.enqueue(e);
            }
        }
    }

    private void lazyPrimAlg(EdgeWeightedGraph g) {
        MinPQ<Edge> pq = new MinPQ<>();
        boolean[] marked = new boolean[g.v()];
        visit(g, 0, marked, pq);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            primMst.enqueue(e);
            if (!marked[v]) {
                visit(g, v, marked, pq);
            }
            if (!marked[w]) {
                visit(g, w, marked, pq);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int i, boolean[] marked, MinPQ<Edge> pq) {
        marked[i] = true;
        for (Edge e : g.adj(i)) {
            if (!marked[e.other(i)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return kruskalMst;
    }

    public double weight() {
        // TODO:
        return 0.0;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);
        MinSpanningTree mst = new MinSpanningTree(g);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%f.2f#n", mst.weight());
    }
}
