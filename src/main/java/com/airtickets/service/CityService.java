package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.City;
import main.java.com.airtickets.repository.io.CityRepositiryImp;

import java.util.List;

public class CityService {
    private CityRepositiryImp cityRepositiryImp = new CityRepositiryImp();

    public void addCity(City city){
        cityRepositiryImp.save(city);
    }

    public List<String> getAllCities() throws FileEmptyException {
        return cityRepositiryImp.getAllCities();
    }
}
