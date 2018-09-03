package main.java.com.airtickets.view.command.mainmenu.factorymethods;
import main.java.com.airtickets.view.command.mainmenu.LoginMainCommand;
import main.java.com.airtickets.view.command.mainmenu.MainCommand;
import main.java.com.airtickets.view.command.mainmenu.MainCommandFactory;

public class LoginComFactory implements MainCommandFactory {
    @Override
    public MainCommand createCommand() {
        return new LoginMainCommand();
    }
}
