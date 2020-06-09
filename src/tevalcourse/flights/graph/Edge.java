package tevalcourse.flights.graph;

import java.util.Objects;

class Edge {
    private final int price;
    private final Node node;

    Edge(int price, Node node) {
        this.price = price;
        this.node = node;
    }

    int getPrice() {
        return price;
    }

    Node getNode() {
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        return price == edge.price
                && Objects.equals(node, edge.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, node);
    }

    @Override
    public String toString() {
        return String.format("Edge to %s, price=%d", node, price);
    }
}
