package main.java.com.airtickets.controller;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.City;
import main.java.com.airtickets.service.CityService;

import java.util.List;

public class CityController {
    private CityService cityService = new CityService();

    public void createNewCity(City city){
        cityService.addCity(city);
    }

    public List<String> getAllCities() throws FileEmptyException {
        return cityService.getAllCities();
    }
}
