package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Producer;
import bg.tuvarna.springboot.Cinema.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/{username}/producers")
    public List<Producer> getAllProducers(@PathVariable String username) {
        return producerService.findAll();
    }

    @GetMapping("/{username}/producers/{id}")
    public Producer getProducer(@PathVariable String username, @PathVariable long id) {
        return producerService.findById(id);
    }

    @DeleteMapping("/{username}/producers/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable String username, @PathVariable long id) {

        producerService.deleteById(id);

        Producer producer = producerService.findById(id);

        if (producer == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{username}/producers/{id}")
    public ResponseEntity<Producer> updateProducer(@PathVariable String username, @PathVariable long id,
                                                   @RequestBody Producer producer) {

        Producer updatedProducer = producerService.createOrUpdateProducer(producer);

        return new ResponseEntity<Producer>(producer, HttpStatus.OK);
    }

    @PostMapping("/{username}/producers")
    public ResponseEntity<Void> createProducer(@PathVariable String username, @RequestBody Producer producer) {

        producer = producerService.createOrUpdateProducer(producer);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
