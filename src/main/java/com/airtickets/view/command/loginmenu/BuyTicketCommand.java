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
                id++;
            }
        } catch (FileEmptyException e) {
            id = 1L;
        }
        return id;
    }

    private void createTicket() throws CloseCommandException {
        String route = selectTicketParametr(Commands.ROUTE, routeController, flightController);
        String date = selectTicketParametr(Commands.DATE, routeController, flightController);
        try {
            String ticketType = selectTypeTicket(route, date, routeController, flightController);
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

}
