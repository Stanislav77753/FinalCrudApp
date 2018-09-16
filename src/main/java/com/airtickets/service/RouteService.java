package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.EntityNotExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Route;
import main.java.com.airtickets.repository.io.RouteRepositoryImp;

import java.util.List;

public class RouteService {
    private RouteRepositoryImp routeRepositoryImp = new RouteRepositoryImp();

    public void addRoute(Route route){
        routeRepositoryImp.save(route);
    }

    public List<String> getAllRoutes() throws FileEmptyException {
        return routeRepositoryImp.getAllRoutes();
    }

    public String getRouteByName(String routeName) throws FileEmptyException, EntityNotExistsException {
        List<String> routes = routeRepositoryImp.getAllRoutes();
        for(String route: routes){
            String[] routeArray = route.split(",");
            if(routeArray[1].equals(routeName)){
                return route;
            }
        }
        throw new EntityNotExistsException("\u001B[31m" + "THIS ROUTE IS NOT EXISTS");
    }

    public Long getIdByRouteName(String routeName) throws FileEmptyException, EntityNotExistsException {
        List<String> routes = routeRepositoryImp.getAllRoutes();
        for(String route: routes){
            String[] routeArray = route.split(",");
            if(routeArray[1].equals(routeName)){
                return new Long(routeArray[0]);
            }
        }
        throw new EntityNotExistsException("\u001B[31m" + "THIS ROUTE IS NOT EXISTS");
    }
}
