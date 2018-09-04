package main.java.com.airtickets.controller;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.service.FlightService;

import java.util.List;

public class FlightController {
    private FlightService flightService = new FlightService();

    public void createFlight(Flight flight){
        flightService.addFlight(flight);
    }

    public List<String> getAllFlights() throws FileEmptyException {
        return flightService.getAllFlights();
    }

    public void updateFlight(Flight flight){
        flightService.updateFlight(flight);
    }
}
