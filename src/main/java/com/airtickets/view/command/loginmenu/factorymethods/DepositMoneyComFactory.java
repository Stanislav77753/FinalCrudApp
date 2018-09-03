package main.java.com.airtickets.view.command.loginmenu.factorymethods;

import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;
import main.java.com.airtickets.view.command.loginmenu.DepositMoneyCommand;

public class DepositMoneyComFactory implements LoginCommandFactory {
    @Override
    public LoginCommand createCommand(User user) {
        return new DepositMoneyCommand(user);
    }
}
