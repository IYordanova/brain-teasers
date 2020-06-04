package tevalcourse.flights.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Node {
    private final String airportCode;
    private final Map<String, Edge> connections;

    Node(String airportCode) {
        this(airportCode, new HashMap<>());
    }

    Node(String airportCode, Map<String, Edge> connections) {
        this.airportCode = airportCode;
        this.connections = connections;
    }


    void addConnection(String from, Edge to) {
        connections.put(from, to);
    }

    String getAirportCode() {
        return airportCode;
    }

    Collection<Edge> getAllConnections() {
        return connections.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(airportCode, node.airportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportCode);
    }

    @Override
    public String toString() {
        return String.format("Node - %s, connections to %s", airportCode, connections);
    }
}
