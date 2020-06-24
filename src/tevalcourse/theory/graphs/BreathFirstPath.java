package tevalcourse.theory.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;


/*
  Application - routing, shortest path
 */
public class BreathFirstPath {
    private final boolean[] visited;
    private final int[] edgeTo;
    private final int s;


    public BreathFirstPath(Graph graph, int s) {
        this.visited = new boolean[graph.v()];
        this.edgeTo = new int[graph.v()];
        this.s = s;
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    q.enqueue(w);
                    visited[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        Graph graph = new UndirectedGraph(in);
        BreathFirstPath paths = new BreathFirstPath(graph, s);
        for (int v = 0; v < graph.v(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println(v);
            }
        }
    }
}
