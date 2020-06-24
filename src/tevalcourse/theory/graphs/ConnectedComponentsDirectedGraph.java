package tevalcourse.theory.graphs;


public class ConnectedComponentsDirectedGraph {

    private boolean[] visited;
    private int[] id;
    private int count;


    public ConnectedComponentsDirectedGraph(DirectedGraph graph) {
        this.visited = new boolean[graph.v()];
        this.id = new int[graph.v()];
        DeptFirstPaths dsf = new DeptFirstPaths(graph.reverse(), 0);
        for (int v : dsf.getReversePost()) {
            if (!visited[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }


    private void dfs(Graph graph, int v) {
        visited[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(graph, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public static void main(String[] args) {

    }
}
