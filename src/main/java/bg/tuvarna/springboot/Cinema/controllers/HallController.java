package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Hall;
import bg.tuvarna.springboot.Cinema.services.HallService;
import bg.tuvarna.springboot.Cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private SeatService seatService;

    @GetMapping("{pathname}/halls")
    public List<Hall> getAllHalls(@PathVariable String pathname) {
        return hallService.findAll();
    }

    @GetMapping("/{pathname}/halls/{id}")
    public Hall getHall(@PathVariable String pathname, @PathVariable long id) {
        return hallService.findById(id);
    }

    @DeleteMapping("/{pathname}/halls/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable String pathname, @PathVariable long id) {

        hallService.deleteById(id);

        Hall hall = hallService.findById(id);

        if (hall == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/halls/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable String pathname, @PathVariable long id,
                                           @RequestBody Hall hall) {

        Hall updatedHall = hallService.createOrUpdateHall(hall);

        return new ResponseEntity<Hall>(hall, HttpStatus.OK);
    }

    @PostMapping("/{pathname}/halls")
    public ResponseEntity<Void> createHall(@PathVariable String pathname, @RequestBody Hall hall) {

        hall = hallService.createOrUpdateHall(hall);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(hall.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
