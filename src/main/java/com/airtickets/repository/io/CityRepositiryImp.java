package main.java.com.airtickets.repository.io;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.City;
import main.java.com.airtickets.repository.CityRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CityRepositiryImp implements CityRepository {
    private File cities = new File("src/main/resources/cities.txt");

    @Override
    public void save(City city) {
        Long id;
        try(BufferedWriter out = new BufferedWriter(new FileWriter(cities, true))) {
            try {
                id = getId(cities) + 1L;
            } catch (FileEmptyException e) {
                id = 1L;
            }
            out.write(id + ","  + city.getName() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(City city) {

    }

    @Override
    public void update(City city) {

    }

    @Override
    public City getById(Long aLong) {
        return null;
    }

    public List<String> getAllCities() throws FileEmptyException {
        return getAll(cities);
    }
}
