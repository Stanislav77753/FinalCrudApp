package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityNotExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectSearchTypeException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;

import java.util.List;

public class FindFlightCommand implements LoginCommand {
    private User user;
    private FlightController flightController = new FlightController();
    private RouteController routeController = new RouteController();

    public FindFlightCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectSearchTypeException, CloseCommandException {
        try {
            List<String> flights = flightController.getAllFlights();
            String searchType = ConsoleHelper.enterEntityParametrs("searchType");
            if(searchType.equals("route")){
                boolean routeFlag = false;
                String[] route = routeController.getRouteByName(ConsoleHelper.
                        enterEntityParametrs("route")).split(",");
                for(String flight: flights){
                    String[] flightArray = flight.split(",");
                    if(flightArray[2].equals(route[0])){
                        System.out.println(flightArray[1] + "\n" +  route[1] + "\n" + "Economy seats - "
                                + (new Integer(route[2]) - new Integer(flightArray[3])) + ". Price = " + route[4]
                                + "\n" + "Business seats - " + (new Integer(route[3]) - new Integer(flightArray[4]))
                                + ". Price = " + route[5] + "\n");
                        routeFlag = true;
                    }
                }
                if(!routeFlag){
                    System.out.println("this flight is not exists");
                }
            }else if(searchType.equals("date")){
                String date = ConsoleHelper.enterEntityParametrs("date");
                List<String> routes = routeController.getAllRoutes();
                boolean flagDate = false;
                for(String flight: flights){
                    String[] flightArray = flight.split(",");
                    if(flightArray[1].equals(date)){
                        for(String route: routes){
                            String[] routeArray = route.split(",");
                            if(flightArray[2].equals(routeArray[0])){
                                System.out.println(flightArray[1] + "\n" +  routeArray[1] + "\n" + "Economy seats - "
                                        + (new Integer(routeArray[2]) - new Integer(flightArray[3])) + ". Price = "
                                        + routeArray[4] + "\n" + "Business seats - "
                                        + (new Integer(routeArray[3]) - new Integer(flightArray[4]))
                                        + ". Price = " + routeArray[5] + "\n");
                                flagDate = true;
                                break;
                            }
                        }

                    }
                }
                if(!flagDate){
                    System.out.println("On this date not exist flights");
                }
            }else{
                throw new IncorrectSearchTypeException("Yoe entered incorrect search type");
            }
        } catch (FileEmptyException e) {
            System.out.println("At the moment there are no flights"); //???????
        } catch (EntityNotExistsException e) {
            System.out.println(e.getMessage());;// ???????
        }

    }
}
