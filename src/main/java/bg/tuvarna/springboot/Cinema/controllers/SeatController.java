package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Row;
import bg.tuvarna.springboot.Cinema.models.Seat;
import bg.tuvarna.springboot.Cinema.services.RowService;
import bg.tuvarna.springboot.Cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class SeatController {

    @Autowired
    private SeatService seatService;

    @Autowired
    private RowService rowService;

    @DeleteMapping("/{pathname}/seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable String pathname, @PathVariable long id) {

        Row row = rowService.findById(id);

        if (row.getSeats().size() == 0)
            return ResponseEntity.noContent().build();

        seatService.deleteById(row.getSeats().get(row.getSeats().size() - 1).getId());
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{pathname}/seats/{id}")
    public ResponseEntity<Seat> create(@PathVariable String pathname, @PathVariable long id) {

        Row row = rowService.findById(id);

        Seat seat = new Seat(0, row, row.getSeats().size());

        seat = seatService.createOrUpdateSeat(seat);

        return new ResponseEntity<Seat>(seat, HttpStatus.OK);
    }
}

