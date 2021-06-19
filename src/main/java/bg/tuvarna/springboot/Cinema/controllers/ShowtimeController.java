package bg.tuvarna.springboot.Cinema.controllers;


import bg.tuvarna.springboot.Cinema.models.Showtime;
import bg.tuvarna.springboot.Cinema.services.ShowtimeService;
import bg.tuvarna.springboot.Cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{pathname}/showtimes")
    public List<Showtime> getAllShowtimes(@PathVariable String pathname) {
        return showtimeService.findAll();

    }

    @PostMapping("/{pathname}/showtimes/date")
    public List<Showtime> getShowtimesByDate(@PathVariable String pathname, @RequestBody Date date){

        return showtimeService.findAllByDate(date);

    }

    @GetMapping("/{pathname}/showtimes/{id}")
    public Showtime getShowtime(@PathVariable String pathname, @PathVariable long id) {
        return showtimeService.findById(id);
    }

    @DeleteMapping("/{pathname}/showtimes/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable String pathname, @PathVariable long id) {

        if(ticketService.checkTicketExist(id))
        showtimeService.deleteById(id);

        Showtime Showtime = showtimeService.findById(id);

        if (Showtime == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/showtimes/{id}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable String pathname, @PathVariable long id,
                                                   @RequestBody Showtime showtime) {

        Showtime checkShowtime = showtimeService.checkShowtime(showtime);
        if (checkShowtime != null) {
            return new ResponseEntity<Showtime>(HttpStatus.NOT_FOUND);
        }

        showtime = showtimeService.createOrUpdateShowtime(showtime);

        return new ResponseEntity<Showtime>(showtime, HttpStatus.OK);
    }
}
