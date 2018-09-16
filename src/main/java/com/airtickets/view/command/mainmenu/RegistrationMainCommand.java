package main.java.com.airtickets.view.command.mainmenu;
import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;
import main.java.com.airtickets.model.Logger;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;


public class RegistrationMainCommand extends MainCommand {
    private UserController userController = new UserController();

    @Override
    public void execute() throws CloseCommandException {
        User user = new User(null, setLogin("registration"), setPassword(), setName("name"),setName("lastname"));
        userController.createNewUser(user);
        Logger.printLog("Successful registration. Login - " + user.getLogin());
        ConsoleHelper.loginMenu(user);
    }

    private String setName(String nameOrLastName) throws CloseCommandException {
        String name = "";
        boolean checkFlag = false;
        do{
            try {
                if(nameOrLastName.equals("name")){
                    name = ConsoleHelper.enterEntityParametrs(Commands.NAME);
                }else{
                    name = ConsoleHelper.enterEntityParametrs(Commands.LAST_NAME);
                }
                checkFlag = Validator.checkName(name);
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage() + "NAME OR LASTNAME");
            }
        }while (!checkFlag);
        return name;
    }






}
