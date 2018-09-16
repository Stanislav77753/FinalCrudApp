package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.controller.TicketController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;
import java.util.List;

public class BuyTicketCommand extends LoginCommand {
    private User user;
    private RouteController routeController = new RouteController();
    private FlightController flightController = new FlightController();
    private TicketController ticketController = new TicketController();

    public BuyTicketCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {
       createTicket();
    }

    private Double getPrice(String typeSeats, String route){
        Double price = 0.0;
        try {
            String[] routeArray = routeController.getRouteByName(route).split(",");
            if(typeSeats.equals("economy")){
                price = new Double(routeArray[4]);
            }else{
                price = new Double((routeArray[5]));
            }
        } catch (EntityNotExistsException e) {
            e.printStackTrace();
        } catch (FileEmptyException e) {
            e.printStackTrace();
        }
        return price;
    }

    private boolean checkBalance(Double price) throws BalanceException {
        if(price > user.getBalance()){
            throw new BalanceException("\u001B[31m" + "you don't have enough money");
        }else{
            return true;
        }
    }

    private Long getIdForTicket(){
        Long id = 1L;
        try {
            List<String> tickets = ticketController.getAllTickets();
            for(String ticket: tickets){
                String[] ticketArray = ticket.split(",");
                id = new Long(ticketArray[0]);
            }
            id++;
        } catch (FileEmptyException e) {
            id = 1L;
        }
        return id;
    }

    private void createTicket() throws CloseCommandException {
        String route = selectTicketParametr(Commands.ROUTE);
        String date = selectTicketParametr(Commands.DATE);
        try {
            String ticketType = selectTypeTicket(route, date);
            Double price = getPrice(ticketType, route);
            if(checkBalance(price)){
                Ticket ticket = new Ticket(getIdForTicket(),date, flightController.getId(date,
                        routeController.getIdByRouteName(route)), ticketType,price);
                ticket.setUserName(user.getLastName() + " " + user.getName());
                ticketController.createTicket(ticket);
                updateFlight(ticket);
                user.decrementBalance(price);
                user.addTicketsId(ticket.getId());
            }
        } catch (VacantTicketException | BalanceException e) {
            System.out.println(e.getMessage());
            throw new CloseCommandException("cancel");
        } catch (FileEmptyException | EntityNotExistsException e){
            e.printStackTrace();
        }
    }

    private void updateFlight(Ticket ticket){
        try {
            List<String> flights = flightController.getAllFlights();
            for(String flight: flights){
                String[] flightArray = flight.split(",");
                if(new Long(flightArray[0]).equals(ticket.getFlightId())){
                    Flight flightUpdate = new Flight(new Long(flightArray[0]), flightArray[1], new Long(flightArray[2]));
                    flightUpdate.setBoughtEconomy(new Integer(flightArray[3]));
                    flightUpdate.setBoughtBusiness(new Integer(flightArray[4]));
                    if(ticket.getType().equals("economy")){
                        flightUpdate.incrementBoughtEconomy();
                    }else{
                        flightUpdate.incrementBoughtBusiness();
                    }
                    flightController.updateFlight(flightUpdate);
                }
            }
        } catch (FileEmptyException e) {
        }
    }

    private String selectTicketParametr(Commands command) throws CloseCommandException {
        String parametr;
        do{
            try{
                parametr = ConsoleHelper.enterEntityParametrs(command);
                if(parametr.equals("cancel")){
                    throw new CloseCommandException("cancel");
                }else if(command == Commands.ROUTE){
                    if(checkRoute(parametr)){
                        return parametr;
                    }else{
                        throw new IncorrectEntityException("\u001B[31m" + "THIS ROUTE IS NOT EXISTS");
                    }
                } else if(command == Commands.DATE){
                    if(checkDate(parametr)){
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

    private String selectTypeTicket(String route, String date) throws CloseCommandException,
            VacantTicketException {
        String typeTicket;
        do{
            typeTicket = ConsoleHelper.enterEntityParametrs(Commands.TYPE_TICKET);
            try {
                if(checkType(typeTicket)){
                    if(checkVacantSeats(typeTicket, route, date)){
                        return typeTicket;
                    }
                }
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage());
            }
        }while(true);
    }

    private boolean checkRoute(String route){
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

    private boolean checkDate(String date){
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

    private boolean checkVacantSeats(String typeTicket, String route, String date) throws VacantTicketException {
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
