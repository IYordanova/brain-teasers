package tevalcourse.flights.graph;

public class Flight {
    private final String from;
    private final String to;
    private final int price;


    public Flight(String from, String to, int price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }

    String getFrom() {
        return from;
    }

    String getTo() {
        return to;
    }

    int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Flight[%s - %s], price=%d", from, to, price);
    }
}
