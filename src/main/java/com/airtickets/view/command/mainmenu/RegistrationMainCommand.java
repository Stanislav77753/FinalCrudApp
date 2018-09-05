package main.java.com.airtickets.view.command.mainmenu;
import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;


public class RegistrationMainCommand extends MainCommand {
    private UserController userController = new UserController();

    @Override
    public void execute() throws CloseCommandException {
        User user = new User(null, setLogin("registration"),
                ConsoleHelper.enterEntityParametrs("password"),
                ConsoleHelper.enterEntityParametrs("name"),
                ConsoleHelper.enterEntityParametrs("lastname"));
        userController.createNewUser(user);
        ConsoleHelper.loginMenu(user);
    }




}
