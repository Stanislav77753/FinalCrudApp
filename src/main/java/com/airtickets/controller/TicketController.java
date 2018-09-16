package main.java.com.airtickets.controller;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.service.TicketService;

import java.util.List;

public class TicketController {
    private TicketService ticketService = new TicketService();

    public void createTicket(Ticket ticket){
        ticketService.addTicket(ticket);
    }

    public List<String> getAllTickets() throws FileEmptyException {
        return ticketService.getAllTickets();
    }

    public Ticket getById(Long id){
        return ticketService.getTicketById(id);
    }

    public void deleteTicket(Ticket ticket){
        ticketService.deleteTicket(ticket);
    }
}
