package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.repository.io.UserRepositoryImp;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepositoryImp userRepositoryImp = new UserRepositoryImp();

    public void addUser(User user){
        userRepositoryImp.save(user);

    }

    public List<String> getAllLogins() throws FileEmptyException {
        List<String> allUsers = userRepositoryImp.getAllUsers();
        List<String> logins = new ArrayList<>();
        for(String user : allUsers) {
            String[] userParametrs = user.split(",");
            logins.add(userParametrs[1]);
        }
        return logins;
    }

    public String getPasswordByLogin(String login){
        String password = "";
        try {
            List<String> allUsers = userRepositoryImp.getAllUsers();
            for(String user: allUsers){
                String[] userParametrs = user.split(",");
                if(login.equals(userParametrs[1])){
                    password = userParametrs[2];
                }
            }
        } catch (FileEmptyException e) {
        }
        return password;
    }
    // builder
    public User getUserByLogin(String login){
        User userByLogin = new User(null, null, null, null, null);
        try {
            List<String> allUsers = userRepositoryImp.getAllUsers();
            for(String user : allUsers){
                String[] userParametrs = user.split(",");
                if(login.equals(userParametrs[1])){
                    userByLogin.setId(new Long(userParametrs[0]));
                    userByLogin.setLogin(userParametrs[1]);
                    userByLogin.setPassword(userParametrs[2]);
                    userByLogin.setRoleId(new Integer(userParametrs[3]));
                    userByLogin.setName(userParametrs[4]);
                    userByLogin.setLastName(userParametrs[5]);
                    userByLogin.setBalance(new Double(userParametrs[6]));
                    if(userParametrs[7].trim().length() > 2 && userParametrs[7].trim().length() < 4){
                        String tickets = userParametrs[7].substring(1, 2);
                        userByLogin.addTicketsId(new Long(tickets));
                    }else if(userParametrs[7].trim().length() > 3 ){
                        int lastIndex = userParametrs[7].trim().length() - 1;
                        String tickets = userParametrs[7].substring(1, lastIndex - 1);
                        String[] ticketsArray = tickets.split(";");
                        for(String str: ticketsArray){
                            userByLogin.addTicketsId(new Long(str));
                        }
                    }
                }
            }
        } catch (FileEmptyException e) {
        }
        return userByLogin;
    }

    public void updateUser(User user){
        userRepositoryImp.update(user);
    }
}
