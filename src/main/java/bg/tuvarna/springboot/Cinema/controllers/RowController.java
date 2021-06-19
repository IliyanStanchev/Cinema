package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Hall;
import bg.tuvarna.springboot.Cinema.models.Row;
import bg.tuvarna.springboot.Cinema.models.Seat;
import bg.tuvarna.springboot.Cinema.services.HallService;
import bg.tuvarna.springboot.Cinema.services.RowService;
import bg.tuvarna.springboot.Cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class RowController {

    @Autowired
    private RowService rowService;

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @GetMapping("/{pathname}/rows/{id}")
    public List<Row> getAllRows(@PathVariable String pathname, @PathVariable long id) {
        return rowService.getHallRows(id);
    }

    @DeleteMapping("/{pathname}/rows/{id}")
    public ResponseEntity<Void> deleteRow(@PathVariable String pathname, @PathVariable long id) {

        Hall hall = hallService.findById(id);

        if (hall.getRows().size() == 0)
            return ResponseEntity.noContent().build();

        Row row = hall.getRows().get(hall.getRows().size() - 1);

        for (Seat seat : row.getSeats()) {
            seatService.deleteById(seat.getId());
        }

        rowService.deleteById(row.getId());

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{pathname}/rows/{id}")
    public ResponseEntity<Row> createRow(@PathVariable String pathname, @PathVariable long id) {

        Hall hall = hallService.findById(id);

        Row row = new Row(0, hall, hall.getRows().size());

        row = rowService.createOrUpdateRow(row);

        return new ResponseEntity<Row>(row, HttpStatus.OK);
    }

}
