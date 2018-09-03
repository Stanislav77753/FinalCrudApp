package main.java.com.airtickets.validator;

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
}
