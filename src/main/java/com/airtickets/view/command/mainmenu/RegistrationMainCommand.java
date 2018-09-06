package main.java.com.airtickets.view.command.mainmenu;
import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.ConsoleHelper;
import main.java.com.airtickets.view.command.Commands;


public class RegistrationMainCommand extends MainCommand {
    private UserController userController = new UserController();

    @Override
    public void execute() throws CloseCommandException {
        User user = new User(null, setLogin("registration"), setPaswword(), setName("name"),setName("lastname"));
        userController.createNewUser(user);
        ConsoleHelper.loginMenu(user);
    }

    private String setName(String nameOrLastName) throws CloseCommandException {
        String name = "";
        boolean checkFlag = false;
        do{
            try {
                if(nameOrLastName.equals("name")){
                    name = ConsoleHelper.enterEntityParametrs(Commands.Name);
                }else{
                    name = ConsoleHelper.enterEntityParametrs(Commands.LastName);
                }
                checkFlag = Validator.checkName(name);
            } catch (IncorrectEntityException e) {
                System.out.println(e.getMessage() + nameOrLastName);;
            }
        }while (!checkFlag);
        return name;
    }






}
