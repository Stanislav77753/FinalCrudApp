package main.java.com.airtickets.view.command.mainmenu;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.view.ConsoleHelper;

import java.util.List;

public abstract class MainCommand {
    UserController userController = new UserController();


    public abstract void execute() throws CloseCommandException;

    /**
     * This method create unique login
     * @return
     */
    public String setLogin(String command){
        String login;
        boolean checkFlag;
        boolean checkLogin = true;
        do{
           login = ConsoleHelper.enterLogin();
           checkFlag = checkLogin(login);
           if(checkFlag && command.equals("login")){
               checkLogin = false;
           }else if(!checkFlag && command.equals("login")){
               System.out.println("You entered incorrect login");
           } else if(!checkFlag && command.equals("registration")){
               checkLogin = false;
           }else if(checkFlag && command.equals("registration")){
               System.out.println("This login already exists");
           }
        }while (checkLogin);
        return login;
    }

    /**
     * This method check login on uniqueness
     * @param login
     * @return
     */
    private boolean checkLogin(String login){
        boolean checkFlag = false;
        List<String> logins;
        try {
            logins = userController.getAllLogins();
            for(String log: logins){
                if(login.equals(log)){
                    checkFlag = true;
                    break;
                }
            }
        } catch (FileEmptyException e) {
            //in log
        }
        return checkFlag;
    }

}
