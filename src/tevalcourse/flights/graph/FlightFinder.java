package tevalcourse.flights.graph;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class FlightFinder {
    private final Map<String, Node> graph;

    public FlightFinder(List<Flight> flights) {
        this.graph = buildGraph(flights);
    }

    private Map<String, Node> buildGraph(List<Flight> flights) {
        Map<String, Node> nodes = new HashMap<>();
        for (Flight flight : flights) {
            nodes.putIfAbsent(flight.getFrom(), new Node(flight.getFrom()));
//            nodes.putIfAbsent(flight.getTo(), new Node(flight.getTo()));
            Node fromNode = nodes.get(flight.getFrom());
            Node toNode = new Node(flight.getTo()) ;//nodes.get(flight.getTo());
            fromNode.addConnection(flight.getTo(), new Edge(flight.getPrice(), toNode));
        }
        return nodes;
    }

    public List<String> findConnections(String start, String dest) {
        if (!graph.containsKey(start) || !graph.containsKey(dest)) {
            return Collections.emptyList();
        }
        Set<Route> result = new TreeSet<>();
        Map<String, List<Route>>  prices = new HashMap<>();
        Queue<Route> toTraverse = getInitialTraversalQueue(start);
        while (!toTraverse.isEmpty()) {
            Route route = toTraverse.poll();
            if (route.getAirports().getLast().equals(dest)) {
                result.add(route);
            } else {
                toTraverse.addAll(processRoute(graph, prices, route));
            }
        }
        return result.stream()
                .sequential()
                .map(Route::toString)
                .collect(Collectors.toList());
    }

    private Queue<Route> getInitialTraversalQueue(String start) {
        Queue<Route> toTraverse = new ConcurrentLinkedQueue<>();
        LinkedList<String> airports = new LinkedList<>();
        airports.add(start);
        toTraverse.add(new Route(0, airports));
        return toTraverse;
    }

    private List<Route> processRoute(Map<String, Node> graph, Map<String, List<Route>> toTraverse, Route route) {
        String code = route.getAirports().getLast();
        List<Route> result = new ArrayList<>();
//        if (!prices.containsKey(code) || prices.get(code) > route.getPrice()) {
//            prices.put(code, route.getPrice());
        if(!toTraverse.containsKey(code) || getMaxSoFar(toTraverse, code) > route.getPrice()){
            Node nextAirport = graph.get(code);
            if(nextAirport != null) {
                for (Edge edge : nextAirport.getAllConnections()) {
                    int price = route.getPrice() + edge.getPrice();
                    LinkedList<String> airports = new LinkedList<>(route.getAirports());
                    airports.add(edge.getNode().getAirportCode());
                    result.add(new Route(price, airports));
                }
            }
        }
        return result;
    }

    private Integer getMaxSoFar(Map<String, List<Route>> toTraverse, String code) {
        return toTraverse.get(code).stream().map(Route::getPrice).max(Integer::compareTo).orElse(0);
    }

}