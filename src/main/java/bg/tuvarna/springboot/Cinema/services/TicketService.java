package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Ticket;
import bg.tuvarna.springboot.Cinema.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();

        if (tickets.size() > 0) {
            return tickets;
        } else {
            return new ArrayList<Ticket>();
        }
    }

    public Ticket findById(long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);

        if (ticketOptional.isPresent()) {
            return ticketOptional.get();
        } else {
            return null;
        }
    }

    public Ticket createOrUpdateTicket(Ticket ticket) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());

        if (ticketOptional.isPresent()) {
            Ticket newEntity = ticketOptional.get();

            newEntity.setPrice(ticket.getPrice());
            newEntity.setSeat(ticket.getSeat());
            newEntity.setShowtime(ticket.getShowtime());
            newEntity.setUser(ticket.getUser());

            newEntity = ticketRepository.save(newEntity);

            return newEntity;
        } else {
            ticketRepository.save(ticket);

            return ticket;
        }
    }

    public void deleteById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);

        if (ticket.isPresent()) {
            ticketRepository.deleteById(id);
        }
    }

    public boolean checkTicketExist(long id) {

       if(ticketRepository.findAllByShowtime(id).size() != 0)
           return false;

       return true;
    }
}
