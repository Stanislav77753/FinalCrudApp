package main.java.com.airtickets.view.command.mainmenu.factorymethods;

import main.java.com.airtickets.view.command.mainmenu.MainCommand;
import main.java.com.airtickets.view.command.mainmenu.MainCommandFactory;
import main.java.com.airtickets.view.command.mainmenu.RegistrationMainCommand;

public class RegistrationComFactory implements MainCommandFactory {
    @Override
    public MainCommand createCommand() {
        return new RegistrationMainCommand();
    }
}
