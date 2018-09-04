package main.java.com.airtickets.view.command.loginmenu;


import main.java.com.airtickets.model.User;

public class ReturnTicketCommand implements LoginCommand {
    private User user;

    public ReturnTicketCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute()  {

    }
}
