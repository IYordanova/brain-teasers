package tevalcourse.flights;

import tevalcourse.BaseTestHelper;
import tevalcourse.flights.graph.Flight;
import tevalcourse.flights.graph.FlightFinder;
import tevalcourse.flights.graph.Route;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightFinderTest extends BaseTestHelper {

    static class TestCase {
        final int limit;
        final String source;
        final String destination;
        final List<Flight> flights;

        public TestCase(int limit, String source, String destination, List<Flight> flights) {
            this.limit = limit;
            this.source = source;
            this.destination = destination;
            this.flights = flights;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> files = Arrays.asList(
                "flights_1",
                "flights_2",
                "flights_3",
                "flights_4",
                "flights_5"
        );
        for (String file : files) {
            runTestCase(createTestCase(readFile(file)));
            System.out.println();
        }
    }

    private static TestCase createTestCase(List<String> lines) {
        List<Flight> flights = new ArrayList<>();
        int numberOfFlights = Integer.parseInt(lines.get(0));
        int i = 1;
        for (; i <= numberOfFlights; i++) {
            String[] line = lines.get(i).trim().split(" ");
            String source = line[0];
            String dest = line[1];
            int price = Integer.parseInt(line[2].trim());
            flights.add(new Flight(source, dest, price));
        }

        int limit = Integer.parseInt(lines.get(i));
        String[] sourceDestinationPair = lines.get(++i).trim().split(" ");
        String source = sourceDestinationPair[0];
        String destination = sourceDestinationPair[1];
        return new TestCase(limit, source, destination, flights);
    }

    private static void runTestCase(TestCase testCase) {
        FlightFinder flightFinder = new FlightFinder(testCase.flights);
        List<Route> routes = flightFinder.findConnections(testCase.source, testCase.destination, testCase.limit);

        if (routes.isEmpty()) {
            System.out.print("<no solution>");
        } else {
            routes.stream()
                    .sequential()
                    .map(Route::toString)
                    .forEach(System.out::println);
        }
    }

}
