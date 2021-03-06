package main.java.com.airtickets.repository.io;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.User;
import main.java.com.airtickets.repository.UsersRepository;

import java.io.*;
import java.util.List;

public class UserRepositoryImp implements UsersRepository {
    private File users = new File("src/main/resources/users.txt");

    @Override
    public void save(User user) {
        Long id;
        try(BufferedWriter out = new BufferedWriter(new FileWriter(users, true))) {
            try {
                id = getId(users) + 1L;
            } catch (FileEmptyException e) {
                id = 1L;
            }
            out.write(id + ","  + user.getLogin() + "," + user.getPassword() + "," + user.getRoleId() + ","
                    + user.getName()+ "," + user.getLastName() + "," + "0.0" + "," + "[]"
                    +  "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllUsers() throws FileEmptyException {
        return getAll(users);
    }

    @Override
    public User getById(Long aLong) {
        return null;
    }


    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {
        List<String> usersList = null;
        try {
            usersList = getAllUsers();
        } catch (FileEmptyException e) {
        }
        try(BufferedWriter out = new BufferedWriter(new FileWriter(users))) {
            for(String userString: usersList){
                String[] userArray = userString.split(",");
                if(!user.getLogin().equals(userArray[1])){
                  out.write(userString + "\r\n");
                }else{
                    List<Long> tickets = user.getTickets();
                    String userTickets;
                    if(tickets.size() == 0){
                        userTickets = "[]";
                    }else{
                        StringBuffer strTickets = new StringBuffer();
                        for(Long ticket: tickets){
                            strTickets.append(ticket + ";");
                        }
                        int lastIndex = strTickets.length() - 1;
                        userTickets = "[" + strTickets.substring(0, lastIndex) + "]";
                    }
                    out.write(userArray[0] + ","  + user.getLogin() + "," + user.getPassword() + ","
                            + user.getRoleId() + "," + user.getName()+ "," + user.getLastName() + ","
                            + user.getBalance() + "," + userTickets + "\r\n");
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
