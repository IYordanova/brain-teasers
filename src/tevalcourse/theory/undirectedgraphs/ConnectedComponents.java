package tevalcourse.theory.undirectedgraphs;

public class ConnectedComponents {
    private final boolean[] visited;
    private final int[] id;
    private int count = 0;

    public ConnectedComponents(Graph graph) {
        this.visited = new boolean[graph.v()];
        this.id = new int[graph.v()];
        for(int i = 0; i < graph.v(); i++){
            if(!visited[i]){
                dfs(graph, i);
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
        return
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
