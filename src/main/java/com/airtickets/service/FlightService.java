package main.java.com.airtickets.service;

import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.repository.io.FlightRepositoryImp;

public class FlightService {
    private FlightRepositoryImp flightRepositoryImp = new FlightRepositoryImp();

    public void addFlight(Flight flight){
        flightRepositoryImp.save(flight);
    }
}
