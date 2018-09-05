package main.java.com.airtickets.view.command.mainmenu;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;
import main.java.com.airtickets.validator.Validator;
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
           login = ConsoleHelper.enterString("login");
            try {
                checkFlag = Validator.checkLogin(login, userController.getAllLogins());
                if(checkFlag && command.equals("login")){
                    checkLogin = false;
                }else if(!checkFlag && command.equals("login")){
                    System.out.println("You entered incorrect login");
                } else if(!checkFlag && command.equals("registration")){
                    checkLogin = false;
                }else if(checkFlag && command.equals("registration")){
                    System.out.println("This login already exists");
                }
            } catch (IncorrectEntityException e) {
            } catch (FileEmptyException e) {
                checkLogin = false;
            }
        }while (checkLogin);
        return login;
    }
}
