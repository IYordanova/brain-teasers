package tevalcourse.theory.undirectedgraphs;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/*
   DFS - visit all places we can get to, mark it as such
    - trace back if nowhere to go

 */
public class DeptFirstPaths {

    private boolean[] visited;
    private int[] edgeTo;
    private int s;


    public DeptFirstPaths(UndirectedGraph graph, int s) {
        this.visited = new boolean[graph.v()];
        this.edgeTo = new int[graph.v()];
        this.s = s;
        dfs(graph, s);
    }

    private void dfs(UndirectedGraph graph, int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(graph, w);
                edgeTo[w] = v;
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
        UndirectedGraph graph = new UndirectedGraph(in);
        DeptFirstPaths paths = new DeptFirstPaths(graph, s);
        for (int v = 0; v < graph.v(); v++) {
            if (paths.hasPathTo(v)) {
                StdOut.println(v);
            }
        }
    }

}
