package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityNotExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectSearchTypeException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.ArrayList;
import java.util.List;

public class FindFlightCommand extends LoginCommand {
    private User user;
    private FlightController flightController = new FlightController();
    private RouteController routeController = new RouteController();

    public FindFlightCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectSearchTypeException, CloseCommandException {
        try {
            List<String> allFlights = flightController.getAllFlights();
            String searchType = ConsoleHelper.enterEntityParametrs(Commands.SEARCH_TYPE);
            if(searchType.equals("cancel")){
                throw new CloseCommandException("cancel");
            }else if(searchType.equals("route")){
                String[] route = routeController.getRouteByName(ConsoleHelper.
                        enterEntityParametrs(Commands.ROUTE)).split(",");
                printFlightsByRoute(findByRoute(allFlights, route[0]), route);
            }else if(searchType.equals("date")){
                printFlightsByDate(findByDate(allFlights), routeController.getAllRoutes());
            }else{
                throw new IncorrectSearchTypeException("\u001B[31m" + "YOU ENTERED INCORRECT SEARCH TYPE");
            }
        } catch (FileEmptyException e) {
            System.out.println(e.getMessage());
        } catch (EntityNotExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> findByRoute(List<String> flights, String route){
        List<String> findFlights = new ArrayList<>();
            for(String flight: flights){
                String[] flightArray = flight.split(",");
                if(flightArray[2].equals(route)){
                    findFlights.add(flight);
                }
            }
        return findFlights;
    }

    private List<String> findByDate(List<String> flights){
        List<String> findFlights = new ArrayList<>();
        String date = ConsoleHelper.enterEntityParametrs(Commands.DATE);
        for(String flight: flights){
            String[] flightArray = flight.split(",");
            if(flightArray[1].equals(date)){
                findFlights.add(flight);
            }
        }
        return findFlights;
    }

    private void printFlightsByRoute(List<String> flights, String[] route){
        for(String flight: flights){
            String[] flightArray = flight.split(",");
            System.out.println("------------------------------------------------------------------------" + "\n"
                    + flightArray[1] + "\n" +  route[1] + "\n" + "Economy seats - "
                    + (new Integer(route[2]) - new Integer(flightArray[3])) + ". Price = "
                    + route[4] + "\n" + "Business seats - "
                    + (new Integer(route[3]) - new Integer(flightArray[4]))
                    + ". Price = " + route[5]);
        }
    }

    private void printFlightsByDate(List<String> flights, List<String> routes){
        for(String flight: flights){
            String[] flightArray = flight.split(",");
            for(String route: routes){
                String[] routeArray = route.split(",");
                if(flightArray[2].equals(routeArray[0])){
                    System.out.println("------------------------------------------------------------------------" + "\n"
                            + flightArray[1] + "\n" +  routeArray[1] + "\n" + "Economy seats - "
                            + (new Integer(routeArray[2]) - new Integer(flightArray[3])) + ". Price = "
                            + routeArray[4] + "\n" + "Business seats - "
                            + (new Integer(routeArray[3]) - new Integer(flightArray[4]))
                            + ". Price = " + routeArray[5]);
                }
            }
        }
    }
}
