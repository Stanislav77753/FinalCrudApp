package main.java.com.airtickets.model;

import java.util.Date;

public class Flight extends BaseEntity {
    private String date;
    private Long routeId;
    public Flight(Long id, String date, Long routeId) {
        super(id);
        this.date = date;
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public Long getRouteId() {
        return routeId;
    }
}
