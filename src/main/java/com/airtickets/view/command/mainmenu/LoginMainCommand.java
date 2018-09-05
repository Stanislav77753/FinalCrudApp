package main.java.com.airtickets.view.command.mainmenu;
import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;

public class LoginMainCommand extends MainCommand {
    private UserController userController = new UserController();
    @Override
    public void execute() throws CloseCommandException {
        login();
    }

    private void login() throws CloseCommandException {
        String login = setLogin("login");
        String truePassword = userController.getPasswordByLogin(login);
        String password = ConsoleHelper.enterEntityParametrs("password");
        if(password.equals(truePassword)){
            User user = userController.getUserByLogin(login);
            ConsoleHelper.loginMenu(user);
        }else{
            System.out.println("You entered wrong password");
        }
    }

}
