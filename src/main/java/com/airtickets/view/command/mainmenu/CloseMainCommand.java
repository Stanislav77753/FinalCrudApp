package main.java.com.airtickets.view.command.mainmenu;

import main.java.com.airtickets.exceptions.CloseCommandException;

public class CloseMainCommand extends MainCommand {

    @Override
    public void execute() throws CloseCommandException {
        throw new CloseCommandException("close program");
    }

}
