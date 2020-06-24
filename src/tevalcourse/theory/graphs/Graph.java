package tevalcourse.theory.graphs;

public interface Graph {
    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    int v();

    int e();
}
