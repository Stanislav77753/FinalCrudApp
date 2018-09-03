package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.model.User;

public class BuyTicketCommand implements LoginCommand {
    private User user;

    public BuyTicketCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {

    }
}
