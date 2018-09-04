package main.java.com.airtickets.view;

import main.java.com.airtickets.controller.UserController;
import main.java.com.airtickets.exceptions.*;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.validator.Validator;
import main.java.com.airtickets.view.command.loginmenu.LoginCommand;
import main.java.com.airtickets.view.command.loginmenu.factorymethods.*;
import main.java.com.airtickets.view.command.mainmenu.MainCommandFactory;
import main.java.com.airtickets.view.command.mainmenu.factorymethods.*;
import main.java.com.airtickets.view.command.mainmenu.MainCommand;
import java.util.Scanner;

public class ConsoleHelper {
    static Scanner scanner = new Scanner(System.in);

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
                command = enterString();
                if(!command.equals("login") && !command.equals("registration") && !command.equals("close")){
                    throw new IncorrectCommandException("You enter incorrect command");
                }
                executeCommand(command, "main", null);
            } catch (CloseCommandException e) {
                break;
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
            }
        }while(true);
    }

    private static String enterString(){
        String command;
        do{
            command = scanner.nextLine().toLowerCase().trim();
            if(!command.equals("")){
                break;
            }
        }while (true);
        return command;
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
                command = enterString();
                if(command.equals("login") || command.equals("registration") || command.equals("close")){
                    throw new IncorrectCommandException("You enter incorrect command");
                }
                executeCommand(command, "login" ,user);
            } catch (CloseCommandException e) {
                break;
            } catch (IncorrectCommandException e){
                System.out.println(e.getMessage());
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
    public static void executeCommand(String commandName, String menu, User user) throws CloseCommandException {
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

    public static String enterLogin(){
        System.out.println("Enter your login");
        return  scanner.nextLine().trim();
    }


    public static String enterEntityParametrs(String parametrName){
        String parametr = null;
        String str;
        if(parametrName.equals("password")){
            do{
                System.out.println("Enter password from more 8 symbols");
                str = scanner.nextLine().trim();
                if(Validator.checkPassword(str)){
                    parametr = str;
                    break;
                }
            }while (true);
        }else if(parametrName.equals("name")){
            do{
                System.out.println("Enter name. Name mustn't have numerals");
                str = scanner.nextLine().trim();
                if(Validator.checkName(str)){
                    parametr = str;
                    break;
                }
            }while (true);
        }else if(parametrName.equals("lastname")){
            do{
                System.out.println("Enter Last name. Last name mustn't have numerals");
                str = scanner.nextLine().trim();
                if(Validator.checkName(str)){
                    parametr = str;
                    break;
                }
            }while (true);
        }else if(parametrName.equals("routeName")){
            System.out.println("Enter route name. Example \"Moscow - Kiev\"");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("seatsEconomy")){
            System.out.println("Enter numbers economy seats");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("seatsBusiness")){
            System.out.println("Enter numbers business seats");
            parametr = scanner.nextLine().trim();
        }
        else if(parametrName.equals("seatsEconomyPrice")){
            System.out.println("Enter price on economy seats");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("seatsBusinessPrice")){
            System.out.println("Enter price on business seats");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("date")){
            System.out.println("Enter date. Example \"01/01/2018\"");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("route")){
            System.out.println("Enter route. Example \"Moscow - Kiev\"");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("searchType")){
            System.out.println("Enter search type to find (route or date)");
            parametr = scanner.nextLine().trim();
        }else if(parametrName.equals("seatsType")){
            System.out.println("Enter sets type (economy or business)");
            parametr = scanner.nextLine().trim();
        }
        return parametr;
    }



    public static Double addBalance(){
        Double balance;
        System.out.println("Enter count of money");
        balance = scanner.nextDouble();
        return balance;
    }


    public void startApp(){
        mainMenu();
    }
}
