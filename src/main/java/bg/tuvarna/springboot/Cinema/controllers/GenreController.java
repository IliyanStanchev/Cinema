package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.Genre;
import bg.tuvarna.springboot.Cinema.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{pathname}/genres")
    public List<Genre> getAllGenres(@PathVariable String pathname) {
        return genreService.findAll();
    }

    @GetMapping("/{pathname}/genres/{id}")
    public Genre getGenre(@PathVariable String pathname, @PathVariable long id) {
        return genreService.findById(id);
    }

    @DeleteMapping("/{pathname}/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable String pathname, @PathVariable long id) {

        genreService.deleteById(id);

        Genre genre = genreService.findById(id);

        if (genre == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable String pathname, @PathVariable long id,
                                             @RequestBody Genre genre) {

        Genre checkGenre = genreService.findByGenre(genre.getName());

        if (checkGenre != null) {
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }

        genre = genreService.createOrUpdateGenre(genre);

        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }

    @PostMapping("/{pathname}/genres")
    public ResponseEntity<Void> createGenre(@PathVariable String pathname, @RequestBody Genre genre) {

        Genre checkGenre = genreService.findByGenre(genre.getName());

        if (checkGenre != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        genre = genreService.createOrUpdateGenre(genre);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genre.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
