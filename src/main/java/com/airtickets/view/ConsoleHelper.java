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
    static Scanner scanner = new Scanner(System.in);

    public void startApp(){
        mainMenu();
    }

    /**
     * This method create main menu...
     */
    private void mainMenu(){
        String command;
        do{
            System.out.println("You are in main menu");
            System.out.println("if you have an account enter login, otherwise enter registration");
            System.out.println("for to close program enter \"close\"");
            try {
                command = enterEntityParametrs(Commands.Command);
                if(!command.equals("login") && !command.equals("registration") && !command.equals("close")
                        && !command.equals("")){
                    throw new IncorrectCommandException("You enter incorrect command");
                }
                executeCommand(command, "main", null);
            } catch (CloseCommandException | LogoutCommandExceprion e) {
                if(e.getMessage().equals("cancel")){
                }else{
                    break;
                }
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            }
        }while(true);
    }

    /**
     * This method create login menu...
     * */
    public static void loginMenu(User user){
        String command;
        System.out.println("You has been login");
        do{
            System.out.println("for to logout enter command \"logout\"");
            try {
                command = enterEntityParametrs(Commands.Command);
                if(command.equals("login") || command.equals("registration") || command.equals("close")
                        || command.equals("")){
                    throw new IncorrectCommandException("You enter incorrect command");
                }
                executeCommand(command, "login" ,user);
            } catch (CloseCommandException e) {
                System.out.println(e.getMessage());
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            } catch (LogoutCommandExceprion logoutCommandExceprion) {
                System.out.println(logoutCommandExceprion.getMessage());
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
            throw new UnknownCommandException("You entered unknown command");
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
            throw new UnknownCommandException("You entered unknown command");
        }
    }


    public static String enterEntityParametrs(Commands commands) {
        System.out.println(commands.getString());
        return scanner.nextLine().trim();
    }
}
