package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.EntityNotExistsException;
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

    public Long getId(String date, Long routeId) throws FileEmptyException, EntityNotExistsException {
        List<String> flights = flightRepositoryImp.getAllFlights();
        for(String flight: flights){
            String[] flightArray = flight.split(",");
            if(date.equals(flightArray[1]) && routeId.equals(new Long(flightArray[2]))){
                return new Long(flightArray[0]);
            }
        }
        throw new EntityNotExistsException("\u001B[31m" + "THIS FLIGHT IS NOT EXISTS");
    }
}
