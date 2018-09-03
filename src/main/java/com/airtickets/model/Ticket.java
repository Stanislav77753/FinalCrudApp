package main.java.com.airtickets.model;

public class Ticket extends BaseEntity {
    private Long flightId;
    private String type;
    private Double price;

    public Ticket(Long id, Long flightId, String type, Double price) {
        super(id);
        this.flightId = flightId;
        this.type = type;
        this.price = price;
    }

    public Long getFlightId() {
        return flightId;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }
}
