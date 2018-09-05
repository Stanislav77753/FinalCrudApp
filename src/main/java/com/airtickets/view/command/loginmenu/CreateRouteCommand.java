package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityAlreadyExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectCommandException;
import main.java.com.airtickets.model.Route;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;

import java.util.List;

public class CreateRouteCommand implements LoginCommand {
    private RouteController routeController = new RouteController();
    private User user;

    public CreateRouteCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws IncorrectCommandException {
        if(!user.getRoleId().equals(new Integer("1"))){
            throw new IncorrectCommandException("Incorrect command");
        }
        boolean createFlag = true;
        do{
            try {
                String newRoute = createRoute();
                String[] newRouteArray = newRoute.split(",");
                routeController.createNewRoute(new Route(null, newRouteArray[1], new Integer(newRouteArray[2]),
                        new Integer(newRouteArray[3]), new Double(newRouteArray[4]), new Double(newRouteArray[5])));
                createFlag = false;
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            } catch (CloseCommandException e) {
                e.printStackTrace();
            }
        }while (createFlag);

    }

    public String createRoute() throws EntityAlreadyExistsException, CloseCommandException {
        String route = "0" + "," + ConsoleHelper.enterEntityParametrs("routeName") + ","
                + ConsoleHelper.enterEntityParametrs("seatsEconomy") + ","
                + ConsoleHelper.enterEntityParametrs("seatsBusiness") + ","
                + ConsoleHelper.enterEntityParametrs("seatsEconomyPrice") + ","
                + ConsoleHelper.enterEntityParametrs("seatsBusinessPrice");
        if(checkRoute(route)){
            return route;
        }else{
            throw new EntityAlreadyExistsException("This route already exists");
        }
    }

    private boolean checkRoute(String newRoute){
        boolean chech = false;
        String[] newRouteArray = newRoute.split(",");
        String newCheckRoute = newRouteArray[1];
        try {
            List<String> routes = routeController.getAllRoutes();
            for(String route: routes){
                String[] routeArray = route.split(",");
                String checkRoute = routeArray[1];
                if(checkRoute.equals(newCheckRoute)){
                    return chech;
                }
            }
            chech = true;
        } catch (FileEmptyException e) {
            chech = true;
            return chech;
        }
        return chech;
    }
}
