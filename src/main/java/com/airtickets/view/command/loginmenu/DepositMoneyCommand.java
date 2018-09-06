package main.java.com.airtickets.view.command.loginmenu;

import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;

public class DepositMoneyCommand implements LoginCommand {
    private User user;

    public DepositMoneyCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() throws CloseCommandException {
        this.user.setBalance(setBalance());
    }

    private Double setBalance() throws CloseCommandException {
        String balance = "";
        boolean checkFlag = false;
        do {
            try {
                balance = ConsoleHelper.enterEntityParametrs(Commands.AddMoney);
                checkFlag = Validator.checkMoney(balance);
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage());
            }
        }while (!checkFlag);
        return new Double(balance);
    }

}
