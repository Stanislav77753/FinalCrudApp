package main.java.com.airtickets.model;

import java.util.Date;

public class Flight extends BaseEntity {
    private String date;
    private Long routeId;
    private Integer boughtEconomy;
    private Integer boughtBusiness;
    public Flight(Long id, String date, Long routeId) {
        super(id);
        this.date = date;
        this.routeId = routeId;
        this.boughtEconomy = 0;
        this.boughtBusiness = 0;
    }

    public void setBoughtEconomy(Integer boughtEconomy) {
        this.boughtEconomy = boughtEconomy;
    }

    public void setBoughtBusiness(Integer boughtBusiness) {
        this.boughtBusiness = boughtBusiness;
    }

    public String getDate() {
        return date;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Integer getBoughtEconomy() {
        return boughtEconomy;
    }

    public void decrementBoughtEconomy() {
        this.boughtEconomy--;
    }
    public void incrementBoughtEconomy() {
        this.boughtEconomy++;
    }

    public Integer getBoughtBusiness() {
        return boughtBusiness;
    }

    public void decrementBoughtBusiness() {
        this.boughtBusiness--;
    }
    public void incrementBoughtBusiness() {
        this.boughtBusiness++;
    }
}
