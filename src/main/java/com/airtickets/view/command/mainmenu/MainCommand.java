package main.java.com.airtickets.view.command.mainmenu;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.EntityAlreadyExistsException;
import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

public abstract class MainCommand {
    UserController userController = new UserController();


    public abstract void execute() throws CloseCommandException;

    /**
     * This method create unique login
     * @return
     */
    public String setLogin(String command) throws CloseCommandException {
        String login;
        boolean checkFlag;
        boolean checkLogin = true;
        do{
           login = ConsoleHelper.enterEntityParametrs(Commands.LOGIN);
            try {
                checkFlag = Validator.checkLogin(login, userController.getAllLogins());
                if(checkFlag && command.equals("login")){
                    checkLogin = false;
                }else if(!checkFlag && command.equals("login")){
                    throw new IncorrectEntityException("\u001B[31m" + "YOU ENTERED INCORRECT LOGIN");
                } else if(!checkFlag && command.equals("registration")){
                    checkLogin = false;
                }else if(checkFlag && command.equals("registration")){
                    throw new EntityAlreadyExistsException("\u001B[31m" + "THIS LOGIN ALREADY EXISTS");
                }
            } catch (IncorrectEntityException | EntityAlreadyExistsException e) {
                System.out.println(e.getMessage());
            } catch (FileEmptyException e) {
                checkLogin = false;
            }
        }while (checkLogin);
        return login;
    }

    public String setPassword() throws CloseCommandException {
        String  password = "";
        boolean checkFlag = false;
        do{
            try {
                password = ConsoleHelper.enterEntityParametrs(Commands.PASSWORD);
                checkFlag = Validator.checkPassword(password);
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage());
            }

        }while (!checkFlag);
        return password;
    }

}
