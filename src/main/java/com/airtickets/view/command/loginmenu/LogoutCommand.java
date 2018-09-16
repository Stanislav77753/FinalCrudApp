package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.LogoutCommandExceprion;
import main.java.com.airtickets.model.Logger;
import main.java.com.airtickets.model.User;

public class LogoutCommand extends LoginCommand {
    private User user;
    private UserController userController = new UserController();

    public LogoutCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws LogoutCommandExceprion {
        userController.updateUser(user);
        Logger.printLog("Successful logout. Login - " + user.getLogin());
        throw new LogoutCommandExceprion("You had logout");
    }
}
