package main.java.com.airtickets.view.command.mainmenu.factorymethods;

import main.java.com.airtickets.view.command.mainmenu.CloseMainCommand;
import main.java.com.airtickets.view.command.mainmenu.MainCommand;
import main.java.com.airtickets.view.command.mainmenu.MainCommandFactory;

public class CloseComFactory implements MainCommandFactory {
    @Override
    public MainCommand createCommand() {
        return new CloseMainCommand();
    }
}
