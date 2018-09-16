package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.controller.TicketController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.List;


public class ReturnTicketCommand extends LoginCommand {
    private User user;
    private FlightController flightController = new FlightController();
    private TicketController ticketController = new TicketController();

    public ReturnTicketCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {
        returnTicket();
    }

    private void returnTicket() throws CloseCommandException {
       Ticket ticket = createTicketById(getTicketId());
       user.incrementBalance(ticket.getPrice());
       user.deleteTicket(ticket.getId());
        try {
            Flight flight = getFlight(ticket.getFlightId());
            if(ticket.getType().equals("economy")){
                flight.decrementBoughtEconomy();
            }else{
                flight.decrementBoughtBusiness();
            }
            flightController.updateFlight(flight);
            ticketController.deleteTicket(ticket);
        } catch (EntityNotExistsException e) {
            System.out.println(e.getMessage());
            throw new CloseCommandException("cancel");
        }
    }

    private Long getTicketId() throws CloseCommandException {
        do{
            try {
                String id = ConsoleHelper.enterEntityParametrs(Commands.ID);
                if(Validator.checkId(id)){
                   return new Long(id);
                }
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage());
            }
        }while (true);
    }

    private Ticket createTicketById(Long id){
        return ticketController.getById(id);
    }

    private Flight getFlight(Long flightId) throws EntityNotExistsException {
        try {
            List<String> flights = flightController.getAllFlights();
            for(String strFlight: flights){
                String[] flightArray = strFlight.split(",");
                if(flightId.equals(new Long(flightArray[0]))){
                    Flight flight = new Flight(flightId, flightArray[1], new Long(flightArray[2]));
                    flight.setBoughtEconomy(new Integer(flightArray[3]));
                    flight.setBoughtBusiness(new Integer(flightArray[4]));
                    return flight;
                }
            }
        } catch (FileEmptyException e) {
            e.printStackTrace();
        }
        throw new EntityNotExistsException("\u001B[31m" + "THIS FLIGHT IS NOT EXISTS");
    }

}
