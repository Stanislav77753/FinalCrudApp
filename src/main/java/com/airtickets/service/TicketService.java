package main.java.com.airtickets.service;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.repository.io.TicketRepositoryImp;

import java.util.List;

public class TicketService {
    private TicketRepositoryImp ticketRepositoryImp = new TicketRepositoryImp();

    public void addTicket(Ticket ticket){
        ticketRepositoryImp.save(ticket);
    }

    public List<String> getAllTickets() throws FileEmptyException {
        return ticketRepositoryImp.getAllTickets();
    }

    public Ticket getTicketById(Long id){
        return ticketRepositoryImp.getById(id);
    }

    public void deleteTicket(Ticket ticket){
        ticketRepositoryImp.delete(ticket);
    }
}
