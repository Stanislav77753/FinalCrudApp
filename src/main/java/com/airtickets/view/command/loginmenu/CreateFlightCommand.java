package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityNotExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectCommandException;
import main.java.com.airtickets.model.Flight;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

public class CreateFlightCommand implements LoginCommand {
    private User user;
    private RouteController routeController = new RouteController();
    private FlightController flightController = new FlightController();

    public CreateFlightCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectCommandException, CloseCommandException {
        if(!user.getRoleId().equals(new Integer("1"))){
            throw new IncorrectCommandException("Incorrect command");
        }
        String[] route;
        boolean chechFlag = true;
        do{
            try {
                route = routeController.getRouteByName(ConsoleHelper.enterEntityParametrs(Commands.RouteName)).
                        split(",");
                flightController.createFlight(new Flight(null, ConsoleHelper.enterEntityParametrs(Commands.Date),
                        new Long(route[0])));
                chechFlag = false;
            } catch (EntityNotExistsException e) {
                System.out.println(e.getMessage());
            } catch (FileEmptyException e) {
                System.out.println(e.getMessage());
            }
        }while (chechFlag);
    }


}
