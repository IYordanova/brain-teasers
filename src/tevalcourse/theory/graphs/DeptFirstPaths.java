package tevalcourse.theory.graphs;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;


public class DeptFirstPaths {

    private final boolean[] visited;
    private final int[] edgeTo;
    private final int s;

    private Stack<Integer> reversePost;

    public DeptFirstPaths(Graph graph, int s) {
        this.visited = new boolean[graph.v()];
        this.edgeTo = new int[graph.v()];
        this.s = s;
        this.reversePost = new Stack<>();
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(graph, w);
            }
        }
        reversePost.push(v);
    }

    public Stack<Integer> getReversePost() {
        return reversePost;
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
        DeptFirstPaths paths = new DeptFirstPaths(graph, s);
        for (int v = 0; v < graph.v(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println(v);
            }
        }
    }

}
