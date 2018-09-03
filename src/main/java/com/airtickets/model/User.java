package main.java.com.airtickets.model;

import java.util.ArrayList;
import java.util.List;

public class User extends NamedEntity {
    private Integer roleId;
    private String login;
    private String password;
    private String lastName;
    private List<Long> tickets;
    private Double balance;
    public User(Long id, String login, String password, String name, String lastName) {
        super(id, name);
        this.roleId = 0;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        tickets = new ArrayList<>();
        this.balance = 0.0;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Long> getTickets() {
        return tickets;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBalance(Double balance) {
        this.balance += balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tickets=" + tickets +
                ", balance=" + balance +
                '}';
    }
}
