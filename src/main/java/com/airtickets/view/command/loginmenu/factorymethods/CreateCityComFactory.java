package main.java.com.airtickets.view.command.loginmenu.factorymethods;

import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.loginmenu.CreateCityCommand;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;

public class CreateCityComFactory implements LoginCommandFactory {
    @Override
    public LoginCommand createCommand(User user) {
        return new CreateCityCommand(user);
    }
}
