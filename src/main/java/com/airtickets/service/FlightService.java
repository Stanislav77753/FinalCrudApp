package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.repository.io.FlightRepositoryImp;

import java.util.List;

public class FlightService {
    private FlightRepositoryImp flightRepositoryImp = new FlightRepositoryImp();

    public void addFlight(Flight flight){
        flightRepositoryImp.save(flight);
    }

    public List<String> getAllFlights() throws FileEmptyException {
        return flightRepositoryImp.getAllFlights();
    }

    public void updateFlight(Flight flight){
        flightRepositoryImp.update(flight);
    }
}
