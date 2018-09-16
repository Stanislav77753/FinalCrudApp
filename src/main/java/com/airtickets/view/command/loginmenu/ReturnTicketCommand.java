package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.controller.TicketController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.Commands;

public class ReturnTicketCommand extends LoginCommand {
    private User user;
    private RouteController routeController = new RouteController();
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
        String route = selectTicketParametr(Commands.ROUTE, routeController, flightController);
        String date = selectTicketParametr(Commands.DATE, routeController, flightController);
        try {
            String ticketType = selectTypeTicket(route, date, routeController, flightController);


        } catch (VacantTicketException | BalanceException e) {
            System.out.println(e.getMessage());
            throw new CloseCommandException("cancel");
        } catch (FileEmptyException | EntityNotExistsException e){
            e.printStackTrace();
        }
    }
}
