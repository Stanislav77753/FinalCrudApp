package main.java.com.airtickets.controller;

import main.java.com.airtickets.exceptions.EntityNotExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Route;
import main.java.com.airtickets.service.RouteService;

import java.util.List;

public class RouteController {
    private RouteService routeService = new RouteService();

    public void createNewRoute(Route route){
        routeService.addRoute(route);
    }

    public List<String> getAllRoutes() throws FileEmptyException {
        return routeService.getAllRoutes();
    }

    public String getRouteByName(String routeName) throws EntityNotExistsException, FileEmptyException {
        return routeService.getRouteByName(routeName);
    }
}
