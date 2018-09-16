package main.java.com.airtickets.view;

import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.view.command.Commands;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;
import main.java.com.airtickets.view.command.loginmenu.factorymethods.*;
import main.java.com.airtickets.view.command.mainmenu.MainCommandFactory;
import main.java.com.airtickets.view.command.mainmenu.factorymethods.*;
import main.java.com.airtickets.view.command.mainmenu.MainCommand;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);
    private static final String mainMenuString = "\u001B[34m" + "You are in main menu" + "\n"
            + "\u001B[33m" + "If you have an account enter \"login\", otherwise enter \"registration\"" + "\n"
            + "For to close program enter \"close\"" + "\u001B[37m";
    private static final String successLogin = "\u001B[32m" + "You has been login";
    private static final String loginMenuString = "\u001B[34m" + "You are in login menu" + "\n"
            + "\u001B[33m" + "for to logout enter command \"logout\"";

    public void startApp(){
        mainMenu();
    }

    /**
     * This method create main menu...
     */
    private void mainMenu(){
        do{
            System.out.println(mainMenuString);
            try {
                executeCommand(enterEntityParametrs(Commands.COMMAND), "main", null);
            } catch (CloseCommandException | LogoutCommandExceprion e) {
                if(!e.getMessage().equals("cancel")){
                    break;
                }
            }
        }while(true);
    }

    /**
     * This method create login menu...
     * */
    public static void loginMenu(User user){
        System.out.println(successLogin);
        do{
            System.out.println(loginMenuString);
            try {
                executeCommand(enterEntityParametrs(Commands.COMMAND), "login" ,user);

            } catch (CloseCommandException e) {
            }  catch (LogoutCommandExceprion logoutCommandExceprion) {
                break;
            }
        }while(true);
    }

    /**
     *
     * @param commandName
     * @param menu - main or login
     * @param user
     * @throws CloseCommandException
     */
    public static void executeCommand(String commandName, String menu, User user) throws CloseCommandException,
            LogoutCommandExceprion {
        try {
            if(menu.equals("main")){
                createCommandByMainMenu(commandName).execute();
            }else if(menu.equals("login")){
                createCommandByLoginMenu(commandName, user).execute();
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage());
        } catch (IncorrectSearchTypeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method(factory method) produce instance of MainCommand (login, registration, close)
     * @param commandName
     * @return
     * @throws UnknownCommandException
     */
    private static MainCommand createCommandByMainMenu(String commandName) throws UnknownCommandException {
        MainCommandFactory mainCommandFactory;
        if(commandName.equals("login")){
            mainCommandFactory = new LoginComFactory();
            return mainCommandFactory.createCommand();
        }else if(commandName.equals("registration")){
            mainCommandFactory = new RegistrationComFactory();
            return mainCommandFactory.createCommand();
        }else if(commandName.equals("close")){
            mainCommandFactory = new CloseComFactory();
            return mainCommandFactory.createCommand();
        }else{
            if(commandName.equals("")){
                throw new UnknownCommandException("\u001B[31m" + "YOU ENTERED EMPTY STRING");
            }else{
                throw new UnknownCommandException("\u001B[31m" + "YOU ENTERED UNKNOWN COMMAND");
            }
        }
    }

    /**
     * This method(factory method) produce instance of LoginCommand (logout, depositMoney)
     * @param commandName
     * @param user
     * @return
     * @throws UnknownCommandException
     */
    private static LoginCommand createCommandByLoginMenu(String commandName, User user) throws UnknownCommandException {
        LoginCommandFactory loginCommandFactory;
        if(commandName.equals("logout")){
            loginCommandFactory = new LogoutComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("add money")){
            loginCommandFactory = new DepositMoneyComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("create new city")){
            loginCommandFactory = new CreateCityComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("create new route")){
            loginCommandFactory = new CreateRouteComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("create new flight")){
            loginCommandFactory = new CreateFlightComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("find flight")){
            loginCommandFactory = new FindFlightComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("buy ticket")){
            loginCommandFactory = new BuyTicketComFactory();
            return loginCommandFactory.createCommand(user);
        }else if(commandName.equals("return ticket")){
            loginCommandFactory = new ReturnTicketComFactory();
            return loginCommandFactory.createCommand(user);
        }else{
            if(commandName.equals("login") || commandName.equals("registration") || commandName.equals("close")){
                throw new UnknownCommandException("\u001B[31m" + "YOU ENTERED INCORRECT COMMAND");
            }else if(commandName.equals("")){
                throw new UnknownCommandException("\u001B[31m" + "YOU ENTERED EMPTY STRING");
            }else{
                throw new UnknownCommandException("\u001B[31m" + "YOU ENTERED UNKNOWN COMMAND");
            }
        }
    }

    public static String enterEntityParametrs(Commands commands) {
        System.out.println(commands.getString());
        return scanner.nextLine().trim();
    }
}
