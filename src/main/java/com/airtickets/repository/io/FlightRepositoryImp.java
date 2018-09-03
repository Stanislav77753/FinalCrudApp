package main.java.com.airtickets.repository.io;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.repository.FlightRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FlightRepositoryImp implements FlightRepository {
    private File flights = new File("src/main/resources/flights.txt");
    @Override
    public void save(Flight flight) {
        Long id;
        try(BufferedWriter out = new BufferedWriter(new FileWriter(flights, true))) {
            try {
                id = getId(flights) + 1L;
            } catch (FileEmptyException e) {
                id = 1L;
            }
            out.write(id + ","  + flight.getDate() + "," + flight.getRouteId() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Flight flight) {

    }

    @Override
    public void update(Flight flight) {

    }

    @Override
    public Flight getById(Long aLong) {
        return null;
    }

    public List<String> getAllFlights() throws FileEmptyException {
        return getAll(flights);
    }
}
