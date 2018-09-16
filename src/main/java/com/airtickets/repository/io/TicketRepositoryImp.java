package main.java.com.airtickets.repository.io;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Ticket;
import main.java.com.airtickets.repository.TicketRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TicketRepositoryImp implements TicketRepository {

    private File tickets = new File("src/main/resources/tickets.txt");


    @Override
    public void save(Ticket ticket) {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(tickets, true))) {
            out.write(ticket.getId() + "," + ticket.getUserName() + "," + ticket.getDate() + "," + ticket.getFlightId()
                    + "," + ticket.getType() + "," + ticket.getPrice() + "," +  "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket ticket) {

    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public Ticket getById(Long aLong) {
        return null;
    }

    public List<String> getAllTickets() throws FileEmptyException {
        return getAll(tickets);
    }
}
