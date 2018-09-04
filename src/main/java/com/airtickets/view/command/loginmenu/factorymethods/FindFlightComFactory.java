package main.java.com.airtickets.view.command.loginmenu.factorymethods;

import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.loginmenu.FindFlightCommand;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;

public class FindFlightComFactory implements LoginCommandFactory {
    @Override
    public LoginCommand createCommand(User user) {
        return new FindFlightCommand(user);
    }
}
