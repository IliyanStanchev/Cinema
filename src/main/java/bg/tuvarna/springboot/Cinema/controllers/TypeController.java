package bg.tuvarna.springboot.Cinema.controllers;


import bg.tuvarna.springboot.Cinema.models.Type;
import bg.tuvarna.springboot.Cinema.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/{pathname}/types")
    public List<Type> getAllTypes(@PathVariable String pathname) {
        return typeService.findAll();
    }

    @GetMapping("/{pathname}/types/{id}")
    public Type getType(@PathVariable String pathname, @PathVariable long id) {
        return typeService.findById(id);
    }

    @DeleteMapping("/{pathname}/types/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable String pathname, @PathVariable long id) {

        typeService.deleteById(id);

        Type type = typeService.findById(id);

        if (type == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/types/{id}")
    public ResponseEntity<Type> updateType(@PathVariable String pathname, @PathVariable long id,
                                           @RequestBody Type type) {

        Type checkType = typeService.findByType(type.getType());

        if (checkType != null) {
            return new ResponseEntity<Type>(HttpStatus.NOT_FOUND);
        }

        type = typeService.createOrUpdateType(type);

        return new ResponseEntity<Type>(type, HttpStatus.OK);
    }

    @PostMapping("/{pathname}/types")
    public ResponseEntity<Void> createType(@PathVariable String pathname, @RequestBody Type type) {

        Type checkType = typeService.findByType(type.getType());

        if (checkType != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        type = typeService.createOrUpdateType(type);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(type.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
