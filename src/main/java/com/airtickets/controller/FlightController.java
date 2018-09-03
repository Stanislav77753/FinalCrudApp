package main.java.com.airtickets.controller;

import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.service.FlightService;

public class FlightController {
    private FlightService flightService = new FlightService();

    public void createFlight(Flight flight){
        flightService.addFlight(flight);
    }
}
