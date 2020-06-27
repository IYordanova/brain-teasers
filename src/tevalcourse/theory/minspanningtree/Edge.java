package tevalcourse.theory.minspanningtree;

public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Get either side of the edge
     *
     * @return either v or w - vertices, building the current edge
     */
    public int either() {
        return v;
    }

    /**
     * Get the other end of the edge
     *
     * @param v -  one side of the edge
     * @return w - the other side of the edge
     */
    public int other(int v) {
        if (v == this.v) return w;
        return v;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public double weight() {
        return weight;
    }
}
