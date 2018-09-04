package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.controller.TicketController;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectCommandException;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;

import java.util.List;

public class BuyTicketCommand implements LoginCommand {
    private User user;
    private Ticket ticket;
    private RouteController routeController = new RouteController();
    private FlightController flightController = new FlightController();
    private TicketController ticketController = new TicketController();

    public BuyTicketCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectCommandException {
        String date = ConsoleHelper.enterEntityParametrs("date");
        String route = ConsoleHelper.enterEntityParametrs("route");
        String seatsType = ConsoleHelper.enterEntityParametrs("seatsType");
        Double price;
        String name = user.getLastName() + " " + user.getName();
        Flight flight;
        try {
            List<String> allRoutes = routeController.getAllRoutes();
            List<String> allFlights = flightController.getAllFlights();
            Long ticketId = 0L;
            try{
                List<String> tickets = ticketController.getAllTickets();
                for(String  str : tickets){
                    ticketId++;
                }
            }catch (FileEmptyException e) {
                ticketId = 1L;
            }


            for(String routes: allRoutes){
                String[] routeArray = routes.split(",");
                if(route.equals(routeArray[1])){
                    for(String flights: allFlights){
                        String[] flightArray = flights.split(",");
                        if(date.equals(flightArray[1]) && routeArray[0].equals(flightArray[2])){
                            if(seatsType.equals("economy")){
                              price = new Double(routeArray[4]);
                            }else if(seatsType.equals("business")){
                                price = new Double(routeArray[5]);
                            }
                            else{
                                throw new IncorrectCommandException("You enter incorrect type");
                            }
                            ticket = new Ticket(ticketId, date, new Long(flightArray[0]),seatsType, price);
                            ticket.setUserName(name);
                            ticketController.createTicket(ticket);
                            user.addTicketsId(ticketId);
                            user.decrementBalance(ticket.getPrice());
                            flight = new Flight(new Long(flightArray[0]), flightArray[1], new Long(flightArray[2]));
                            if(ticket.getType().equals("economy")){
                                flight.incrementBoughtEconomy(1);
                            }else{
                                flight.incrementBoughtBusiness(1);
                            }
                            flightController.updateFlight(flight);
                        }
                    }
                }
            }
        } catch (FileEmptyException e) {
        }
    }
}
