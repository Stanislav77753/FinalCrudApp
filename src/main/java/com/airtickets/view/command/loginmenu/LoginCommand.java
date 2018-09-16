package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.List;


public abstract class LoginCommand {
    public abstract void execute() throws CloseCommandException, IncorrectCommandException, IncorrectSearchTypeException, LogoutCommandExceprion;
    public boolean checkRole(User user){
        if(!user.getRoleId().equals(new Integer("1"))){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkEntity(String entity, List<String> entities){
        if(entities != null){
            for(String strEntity: entities){
                String[] entityArray = strEntity.split(",");
                String checkEntity = entityArray[1];
                if(checkEntity.equals(entity)){
                    return false;
                }
            }
        }
        return true;
    }

    public String selectTicketParametr(Commands command, RouteController routeController,
                                        FlightController flightController) throws CloseCommandException {
        String parametr;
        do{
            try{
                parametr = ConsoleHelper.enterEntityParametrs(command);
                if(parametr.equals("cancel")){
                    throw new CloseCommandException("cancel");
                }else if(command == Commands.ROUTE){
                    if(checkRoute(parametr, routeController)){
                        return parametr;
                    }else{
                        throw new IncorrectEntityException("\u001B[31m" + "THIS ROUTE IS NOT EXISTS");
                    }
                } else if(command == Commands.DATE){
                    if(checkDate(parametr, flightController)){
                        return parametr;
                    }else{
                        throw new IncorrectEntityException("\u001B[31m" + "THE FLIGHT ON THIS DATE IS NOT EXISTS");
                    }
                }
            } catch (IncorrectEntityException e){
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    public String selectTypeTicket(String route, String date, RouteController routeController,
                                    FlightController flightController) throws CloseCommandException,
            VacantTicketException {
        String typeTicket;
        do{
            typeTicket = ConsoleHelper.enterEntityParametrs(Commands.TYPE_TICKET);
            try {
                if(checkType(typeTicket)){
                    if(checkVacantSeats(typeTicket, route, date, routeController, flightController)){
                        return typeTicket;
                    }
                }
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage());
            }
        }while(true);
    }

    public boolean checkRoute(String route, RouteController routeController){
        try {
            List<String> routes = routeController.getAllRoutes();
            for(String strRoute : routes){
                String[] routeArray = strRoute.split(",");
                if(route.equals(routeArray[1])){
                    return true;
                }
            }
        } catch (FileEmptyException e) {
            return false;
        }
        return false;
    }

    public boolean checkDate(String date, FlightController flightController){
        try {
            List<String> flights = flightController.getAllFlights();
            for(String flight: flights){
                String[] flightArray = flight.split(",");
                if(date.equals(flightArray[1])){
                    return true;
                }
            }
        } catch (FileEmptyException e) {
            return false;
        }
        return  false;
    }

    private boolean checkType(String typeTicket) throws CloseCommandException, IncorrectEntityException {
        if(typeTicket.equals("cancel")){
            throw new CloseCommandException("cancel");
        }else if(typeTicket.equals("economy") || typeTicket.equals("business")){
            return true;
        }else{
            throw new IncorrectEntityException("\u001B[31m" + "TOU ENTERED INCORRECT TYPE OF SEATS");
        }
    }

    private boolean checkVacantSeats(String typeTicket, String route, String date, RouteController routeController,
                                     FlightController flightController) throws VacantTicketException {
        try {
            List<String> flights = flightController.getAllFlights();
            String[] routeArray = routeController.getRouteByName(route).split(",");
            for(String flight: flights){
                String[] flightArray = flight.split(",");
                if(date.equals(flightArray[1]) && routeArray[0].equals(flightArray[2])){
                    if(typeTicket.equals("economy")){
                        if(new Integer(flightArray[3]) < new Integer(routeArray[2])){
                            return true;
                        }
                        else{
                            throw new VacantTicketException("\u001B[31m" + "NO VACANT SEATS");
                        }
                    }else{
                        if(new Integer(flightArray[4]) < new Integer(routeArray[3])){
                            return true;
                        }
                        else{
                            throw new VacantTicketException("\u001B[31m" + "NO VACANT SEATS");
                        }
                    }
                }
            }
        } catch (FileEmptyException e) {
            e.printStackTrace();
        } catch (EntityNotExistsException e){
            e.printStackTrace();
        }
        return false;
    }
}
