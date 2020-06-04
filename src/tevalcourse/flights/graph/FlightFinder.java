package tevalcourse.flights.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class FlightFinder {
    private final Map<String, Node> graph;
    private final Comparator<Route> routeComparator = (r1, r2) -> {
        int compare = Integer.compare(r1.getPrice(), r2.getPrice());
        if (compare == 0) {
            return Integer.compare(r1.getAirports().size(), r2.getAirports().size());
        }
        return compare;
    };

    public FlightFinder(List<Flight> flights) {
        this.graph = buildGraph(flights);
    }

    private Map<String, Node> buildGraph(List<Flight> flights) {
        Map<String, Node> nodes = new HashMap<>();
        for (Flight flight : flights) {
            nodes.putIfAbsent(flight.getFrom(), new Node(flight.getFrom()));
            Node fromNode = nodes.get(flight.getFrom());
            Node toNode = new Node(flight.getTo());
            fromNode.addConnection(flight.getTo(), new Edge(flight.getPrice(), toNode));
        }
        return nodes;
    }

    public List<Route> findConnections(String start, String dest, int limit) {
        if (!graph.containsKey(start) || !graph.containsKey(dest)) {
            return Collections.emptyList();
        }
        List<Route> result = findConnections(getInitialTraversalQueue(start), new ArrayList<>(), dest, limit);
        result.sort(routeComparator);
        return result;
    }

    private List<Route> findConnections(Queue<Route> toTraverse, List<Route> result, String dest, int limit) {
        if (toTraverse.isEmpty() || result.size() == limit) return result;
        else {
            Route route = toTraverse.poll();
            if (route.getAirports().getLast().equals(dest)) {
                result.add(route);
            } else {
                toTraverse.addAll(processRoute(graph, route));
            }
            return findConnections(toTraverse, result, dest, limit);
        }
    }

    private Queue<Route> getInitialTraversalQueue(String start) {
        Queue<Route> toTraverse = new ConcurrentLinkedQueue<>();
        LinkedList<String> airports = new LinkedList<>();
        airports.add(start);
        toTraverse.add(new Route(0, airports));
        return toTraverse;
    }

    private List<Route> processRoute(Map<String, Node> graph, Route route) {
        String code = route.getAirports().getLast();
        Node nextAirport = graph.get(code);
        if (nextAirport != null) {
            return nextAirport.getAllConnections()
                    .stream()
                    .map(edge -> {
                        LinkedList<String> airports = new LinkedList<>(route.getAirports());
                        airports.add(edge.getNode().getAirportCode());
                        return new Route(route.getPrice() + edge.getPrice(), airports);
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Integer getMaxSoFar(Map<String, List<Route>> toTraverse, String code) {
        return toTraverse.get(code)
                .stream()
                .map(Route::getPrice)
                .max(Integer::compareTo)
                .orElse(0);
    }

}