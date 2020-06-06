package tevalcourse.flights.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class Route implements Comparable<Route> {
    private final int price;
    private final Deque<String> airports;

    Route(int price, LinkedList<String> airports) {
        this.price = price;
        this.airports = airports;
    }

    @Override
    public int compareTo(Route o) {
        return Integer.compare(this.price, o.price);
    }

    int getPrice() {
        return price;
    }

    Deque<String> getAirports() {
        return airports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return price == route.price
                && Objects.equals(airports, route.airports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, airports);
    }

    @Override
    public String toString() {
        return String.format("%s %d", String.join(" ", airports), price);
    }
}
