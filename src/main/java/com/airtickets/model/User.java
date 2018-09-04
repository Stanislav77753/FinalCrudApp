package main.java.com.airtickets.model;

import java.util.ArrayList;
import java.util.List;

public class User extends NamedEntity {
    private Integer roleId;
    private String login;
    private String password;
    private String lastName;
    private List<Long> ticketsId;
    private Double balance;
    public User(Long id, String login, String password, String name, String lastName) {
        super(id, name);
        this.roleId = 0;
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        ticketsId = new ArrayList<>();
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
        return ticketsId;
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

    public void addTicketsId(Long ticketsId) {
        this.ticketsId.add(ticketsId);
    }

    public void decrementBalance(Double price){
        this.balance -= price;
    }

    public void incrementBalance(Double price){
        this.balance += price;
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tickets=" + ticketsId +
                ", balance=" + balance +
                '}';
    }
}
