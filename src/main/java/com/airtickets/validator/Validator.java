package main.java.com.airtickets.validator;

import main.java.com.airtickets.exceptions.CloseCommandException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;

import java.util.List;

public class Validator {

    public static boolean checkName(String name) throws IncorrectEntityException, CloseCommandException {
        boolean checkFlag = true;
        if(name.equals("cancel")){
            throw new CloseCommandException("cancel");
        }else{
            String[] strName = name.split("");
            for(int i = 0; i < strName.length; i++){
                if(!strName[i].matches("^\\D")){
                    throw new IncorrectEntityException("\u001B[31m" + "YOU ENTERED NOT VALID ");
                }
            }
        }
        return checkFlag;
    }

    public static boolean checkId(String id) throws IncorrectEntityException, CloseCommandException{
        boolean checkFlag = true;
        if(id.equals("cancel")){
            throw new CloseCommandException("cancel");
        }else{
            String[] strName = id.split("");
            for(int i = 0; i < strName.length; i++){
                if(strName[i].matches("^\\D")){
                    throw new IncorrectEntityException("\u001B[31m" + "YOU ENTERED NOT VALID ");
                }
            }
        }
        return checkFlag;
    }
    public static boolean checkPassword(String password) throws IncorrectEntityException, CloseCommandException {
        boolean checkFlag = true;
        if(password.equals("cancel")){
            throw new CloseCommandException("cancel");
        }else{
            if(password.length() < 8){
                throw new IncorrectEntityException("\u001B[31m" + "YOU ENTERED NOT VALID PASSWORD");
            }
        }
        return checkFlag;
    }

    public static boolean checkMoney(String money) throws IncorrectEntityException, CloseCommandException {
        boolean checkFlag;
        if(money.equals("cancel")){
            throw new CloseCommandException("You canceled \"add money\" operation");
        }else{
            String[] strMoney = money.split("");
            for(int i = 0; i < strMoney.length; i++){
                if(strMoney[i].matches("^\\D")){
                    if(!strMoney[i].equals(".")){
                        throw new IncorrectEntityException("You entered not valid value of money");
                    }
                }
            }
        }
        checkFlag = true;
        return checkFlag;
    }
    public static boolean checkLogin(String login, List<String> logins) throws
            IncorrectEntityException, CloseCommandException {
        boolean checkFlag = false;
        if(login.equals("cancel")){
            throw new CloseCommandException("cancel");
        }else{
            if(!login.equals("")){
                for(String log: logins){
                    if(login.equals(log)){
                        checkFlag = true;
                        break;
                    }
                }
            }else{
                throw new IncorrectEntityException("\u001B[31m" + "YOU ENTERED NOT VALID LOGIN");
            }
        }
        return checkFlag;
    }

}
