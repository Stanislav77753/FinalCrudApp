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
        List<String> allTickets;
        try {
            allTickets = getAllTickets();
            try(BufferedWriter out = new BufferedWriter(new FileWriter(tickets))) {
            for(String strTicket: allTickets){
                String[] ticketArray = strTicket.split(",");
                if(!ticket.getId().equals(new Long(ticketArray[0]))){
                        out.write(ticketArray[0] + "," + ticketArray[1] + "," + ticketArray[2] + "," +ticketArray[3]
                                + "," + ticketArray[4] + "," + ticketArray[5] + "," +  "\r\n");

                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public Ticket getById(Long id) {
        List<String> tickets;
        Ticket ticketById;
        try {
            tickets = getAllTickets();
            for(String ticket: tickets){
                String[] ticketArray = ticket.split(",");
                if(id.equals(new Long(ticketArray[0]))){
                    ticketById = new Ticket(id, ticketArray[2], new Long(ticketArray[3]), ticketArray[4],
                            new Double(ticketArray[5]));
                    ticketById.setUserName(ticketArray[1]);
                    return ticketById;
                }
            }
            throw new FileEmptyException("\u001B[31m" + "THIS TICKET IS NOT EXISTS");
        } catch (FileEmptyException e) {
            return null;
        }
    }

    public List<String> getAllTickets() throws FileEmptyException {
        return getAll(tickets);
    }
}
