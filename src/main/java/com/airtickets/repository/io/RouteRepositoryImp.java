package main.java.com.airtickets.repository.io;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Route;
import main.java.com.airtickets.repository.RouteRepository;

import java.io.*;
import java.util.List;

public class RouteRepositoryImp implements RouteRepository {
    private File routes = new File("src/main/resources/routes.txt");

    @Override
    public void save(Route route) {
        Long id;
        try(BufferedWriter out = new BufferedWriter(new FileWriter(routes, true))) {
            try {
                id = getId(routes) + 1L;
            } catch (FileEmptyException e) {
                id = 1L;
            }
            out.write(id + ","  + route.getName() + "," + route.getEconomy() + "," + route.getBusiness() + ","
                    + route.getEconomyPrice() + "," + route.getBusinessPrice() +  "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllRoutes() throws FileEmptyException {
        return getAll(routes);
    }
    @Override
    public Route getById(Long aLong) {
        return null;
    }


    @Override
    public void delete(Route route) {

    }

    @Override
    public void update(Route route) {

    }
}
