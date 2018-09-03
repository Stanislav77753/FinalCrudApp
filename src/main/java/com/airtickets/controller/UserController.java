package main.java.com.airtickets.controller;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.service.UserService;

import java.util.List;

public class UserController {
    private UserService userService = new UserService();

    public void createNewUser(User user){
        userService.addUser(user);
    }

    public List<String> getAllLogins() throws FileEmptyException {
        return userService.getAllLogins();
    }

    public String getPasswordByLogin(String login){
        return userService.getPasswordByLogin(login);
    }

    public User getUserByLogin(String login){
        return userService.getUserByLogin(login);
    }

    public void updateUser(User user){
        userService.updateUser(user);
    }
}
