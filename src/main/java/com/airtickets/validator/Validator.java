package main.java.com.airtickets.validator;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.exceptions.IncorrectEntityException;

import java.util.List;

public class Validator {

    public static boolean checkName(String name){
        boolean checkFlag = true;
        String[] strName = name.split("");
        for(int i = 0; i < strName.length; i++){
            if(!strName[i].matches("^\\D")){
                checkFlag = false;
                break;
            }
        }
        return checkFlag;
    }

    public static boolean checkPassword(String password){
        boolean checkFlag = false;
        if(password.length() >= 8){
            checkFlag = true;
        }
        return checkFlag;
    }

    public static boolean checkMoney(String money){
        boolean checkFlag = false;
        String[] strMoney = money.split("");
        for(int i = 0; i < strMoney.length; i++){
            if(!strMoney[i].matches("^\\D")){
                if(!strMoney[i].equals(".")){
                    checkFlag = false;
                    break;
                }
            }
        }
        checkFlag = true;
        return checkFlag;
    }
    public static boolean checkLogin(String login, List<String> logins) throws IncorrectEntityException {
        boolean checkFlag = false;
        if(!login.equals("")){
            for(String log: logins){
                if(login.equals(log)){
                    checkFlag = true;
                    break;
                }
            }
        }else{
            throw new IncorrectEntityException("You entered not valid login");
        }

        return checkFlag;
    }
}
