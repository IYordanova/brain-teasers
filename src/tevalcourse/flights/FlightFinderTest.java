package tevalcourse.flights;

import tevalcourse.flights.graph.Flight;
import tevalcourse.flights.graph.FlightFinder;
import tevalcourse.flights.graph.Route;

import java.util.Arrays;
import java.util.List;

public class FlightFinderTest {

    public static void main(String[] args) {
        List<Flight> flights = Arrays.asList(
                new Flight("SFO", "JFK", 500),
                new Flight("JFK", "WAW", 800),
                new Flight("JFK", "BSL", 700),
                new Flight("JFK", "ZRH", 850),
                new Flight("ZRH", "BSL", 300),
                new Flight("SFO", "HEL", 1000),
                new Flight("SFO", "MUC", 1100),
                new Flight("SFO", "LHR", 1100),
                new Flight("MUC", "ZRH", 500),
                new Flight("LHR", "BSL", 1),
                new Flight("LHR", "ZRH", 100),
                new Flight("BSL", "ZRH", 1),
                new Flight("SFO", "ZRH", 5000)
        );

        FlightFinder flightFinder = new FlightFinder(flights);
        List<Route> routes = flightFinder.findConnections("SFO", "ZRH", 10);
        routes.stream()
                .sequential()
                .map(Route::toString)
                .forEach(System.out::println);
    }
}
