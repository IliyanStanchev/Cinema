package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Movie;
import bg.tuvarna.springboot.Cinema.models.Ticket;
import bg.tuvarna.springboot.Cinema.services.HallService;
import bg.tuvarna.springboot.Cinema.services.SeatService;
import bg.tuvarna.springboot.Cinema.services.ShowtimeService;
import bg.tuvarna.springboot.Cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class TicketController {

    @Autowired
    TicketService   ticketService;

    @GetMapping("/{pathname}/tickets")
    public List<Ticket> getAllTickets(@PathVariable String pathname) {
        return ticketService.findAll();
    }

    @PutMapping("/{pathname}/customer/hall/ticket")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String pathname,
                                           @RequestBody Ticket ticket) {

        Ticket updatedTicket = ticketService.createOrUpdateTicket(ticket);

        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }
}
