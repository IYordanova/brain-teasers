package tevalcourse.flights.graph;

import java.util.*;
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
        if (!graph.containsKey(start)) {
            return Collections.emptyList();
        }
        List<Route> result = findConnections(getInitialTraversalQueue(start), new ArrayList<>(), dest, limit);
        return result.stream().sorted(routeComparator).limit(limit).collect(Collectors.toList());
    }

    private List<Route> findConnections(Queue<Route> toTraverse, List<Route> result, String dest, int limit) {
        if (toTraverse.isEmpty()) {
            return result;
        }
        Route route = toTraverse.poll();
        Route maxSoFar = getMaxSoFar(result);
        Route minToTraverse = getMinToTraverse(toTraverse);
        if (result.size() == limit) {
            if (maxSoFar != null && minToTraverse != null && maxSoFar.getPrice() < minToTraverse.getPrice()) {
                return result;
            }
        }
        if (route.getAirports().getLast().equals(dest)) {
            result.add(route);
        } else if(maxSoFar == null || maxSoFar.getPrice() > route.getPrice()){
            toTraverse.addAll(processRoute(graph, route));
        }
        return findConnections(toTraverse, result, dest, limit);
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
                        String airportCode = edge.getNode().getAirportCode();
                        if (!route.getAirports().contains(airportCode)) {
                            LinkedList<String> airports = new LinkedList<>(route.getAirports());
                            airports.add(airportCode);
                            return new Route(route.getPrice() + edge.getPrice(), airports);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private Route getMaxSoFar(List<Route> routes) {
        return routes
                .stream()
                .max(routeComparator)
                .orElse(null);
    }


    private Route getMinToTraverse(Queue<Route> routes) {
        return routes
                .stream()
                .min(routeComparator)
                .orElse(null);
    }

}