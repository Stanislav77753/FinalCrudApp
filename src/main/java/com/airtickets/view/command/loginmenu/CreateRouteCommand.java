package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.CityController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityAlreadyExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectCommandException;
import main.java.com.airtickets.model.Logger;
import main.java.com.airtickets.model.Route;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.List;

public class CreateRouteCommand extends LoginCommand {
    private CityController cityController = new CityController();
    private RouteController routeController = new RouteController();
    private User user;

    public CreateRouteCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectCommandException, CloseCommandException {
        if(checkRole(user)){
            boolean createFlag = true;
            do{
                try {
                    routeController.createNewRoute(createRoute());
                    createFlag = false;
                } catch (EntityAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            }while (createFlag);

        } else{
            throw new IncorrectCommandException("\u001B[31m" + "YOU DON'T HAVE THE RIGHTS");
        }
    }

    private Route createRoute() throws EntityAlreadyExistsException {
        String depCity = addCity(Commands.DEPARTURE_CITY);
        String arrCity;
        boolean checkFlag;
        do{
            arrCity = addCity(Commands.ARRIVAL_CITY);
            checkFlag = compareCities(depCity, arrCity);
        }while(!checkFlag);
        String routeName = depCity + " - " + arrCity;
        if(compareRoute(routeName)){
            Logger.printLog("Admin add new route : " + routeName);
            return new Route(null, routeName, new Integer(ConsoleHelper.enterEntityParametrs(Commands.SEATS_ECONOMY)),
                    new Integer(ConsoleHelper.enterEntityParametrs(Commands.SEATS_BUSINESS)),
                    new Double(ConsoleHelper.enterEntityParametrs(Commands.SEATS_ECONOMY_PRICE)),
                    new Double(ConsoleHelper.enterEntityParametrs(Commands.SEATS_BUSINESS_PRICE)));
        }else{
            throw new EntityAlreadyExistsException("This route already exists");
        }
    }

    private boolean checkByCity(String city){
        boolean checkFlag = false;
        try {
            List<String> cities = cityController.getAllCities();
            for(String strCity : cities){
                String[] cityArray = strCity.split(",");
                if(cityArray[1].equals(city)){
                    checkFlag = true;
                }
            }
        } catch (FileEmptyException e) {
            checkFlag = true;
        }
        return checkFlag;
    }

    private String addCity(Commands command){
        String city = ConsoleHelper.enterEntityParametrs(command);
        if (checkByCity(city)) {
            return city;
        }
        return null;
    }

    private boolean compareCities(String dep, String arr){
        boolean checkFlag = true;
        if(dep.equals(arr)){
            checkFlag = false;
        }
        return checkFlag;
    }

    private boolean compareRoute(String route){
        List<String> routes;
        try {
            routes = routeController.getAllRoutes();
        } catch (FileEmptyException e) {
            routes = null;
        }

        return checkEntity(route, routes);
    }
}
