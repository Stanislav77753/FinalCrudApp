package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.FlightController;
import main.java.com.airtickets.controller.RouteController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

import java.util.List;


public abstract class LoginCommand {
    public abstract void execute() throws CloseCommandException, IncorrectCommandException, IncorrectSearchTypeException, LogoutCommandExceprion;
    public boolean checkRole(User user){
        if(!user.getRoleId().equals(new Integer("1"))){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkEntity(String entity, List<String> entities){
        if(entities != null){
            for(String strEntity: entities){
                String[] entityArray = strEntity.split(",");
                String checkEntity = entityArray[1];
                if(checkEntity.equals(entity)){
                    return false;
                }
            }
        }
        return true;
    }


}
