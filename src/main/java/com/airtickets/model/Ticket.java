package main.java.com.airtickets.model;

public class Ticket extends BaseEntity {
    private Long flightId;
    private String type;
    private Double price;
    private String date;
    private String userName;

    public Ticket(Long id, String date, Long flightId, String type, Double price) {
        super(id);
        this.flightId = flightId;
        this.type = type;
        this.price = price;
        this.date = date;
        this.userName = "";
    }

    public String getUserName() {
        return userName;
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

    public String getDate() {
        return date;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
