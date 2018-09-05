package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.ConsoleHelper;

public class DepositMoneyCommand implements LoginCommand {
    private User user;

    public DepositMoneyCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {
        this.user.setBalance(new Double(ConsoleHelper.enterEntityParametrs("add money")));
    }

}
