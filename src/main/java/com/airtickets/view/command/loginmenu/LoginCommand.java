package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.IncorrectCommandException;
import main.java.com.airtickets.exceptions.IncorrectSearchTypeException;


public interface LoginCommand {
    void execute() throws CloseCommandException, IncorrectCommandException, IncorrectSearchTypeException;
}
