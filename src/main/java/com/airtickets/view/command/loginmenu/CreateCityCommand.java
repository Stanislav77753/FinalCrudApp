package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.CityController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.City;
import main.java.com.airtickets.model.Logger;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.List;

public class CreateCityCommand extends LoginCommand {
    private CityController cityController = new CityController();
    private User user;

    public CreateCityCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectCommandException, CloseCommandException {
        if(checkRole(user)){
            boolean createFlag = true;
            do{
                try {
                    cityController.createNewCity(createCity());
                    createFlag = false;
                } catch (EntityAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } catch (IncorrectEntityException e){
                    System.out.println(e.getMessage() + "CITY");
                }
            }while (createFlag);
        }else{
            throw new IncorrectCommandException("\u001B[31m" + "YOU DON'T HAVE THE RIGHTS");
        }
        System.out.println("\u001B[32m" + "City added in database");
    }

    private City createCity() throws EntityAlreadyExistsException, CloseCommandException, IncorrectEntityException {
        String cityName = ConsoleHelper.enterEntityParametrs(Commands.CITY);
        City newCity = null;
        boolean checkCityName = Validator.checkName(cityName);
        if(checkCityName){
            List<String> cities;
            try {
                cities = cityController.getAllCities();
            } catch (FileEmptyException e) {
                    cities = null;
            }
            if(checkEntity(cityName, cities)){
                newCity = new City(null, cityName);
                Logger.printLog("Admin add new city - " + newCity.getName());
            }else{
                throw new EntityAlreadyExistsException("\u001B[31m" + "THIS CITY ALREADY EXISTS");
            }
        }
        return newCity;
    }
}
