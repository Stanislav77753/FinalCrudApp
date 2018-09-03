package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.model.User;

public class LogoutCommand implements LoginCommand {
    private User user;
    private UserController userController = new UserController();

    public LogoutCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {
        userController.updateUser(user);
        throw new CloseCommandException("logout");
    }
}
