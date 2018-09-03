package main.java.com.airtickets.view.command.loginmenu.factorymethods;

import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;

public interface LoginCommandFactory {
    LoginCommand createCommand(User user);
}
