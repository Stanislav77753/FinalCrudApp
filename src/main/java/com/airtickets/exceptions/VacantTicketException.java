package main.java.com.airtickets.exceptions;

public class VacantTicketException extends Exception {
    public VacantTicketException(String message) {
        super(message);
    }
}
