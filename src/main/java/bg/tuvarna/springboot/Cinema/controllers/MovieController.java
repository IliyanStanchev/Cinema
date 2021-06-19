package bg.tuvarna.springboot.Cinema.controllers;


import bg.tuvarna.springboot.Cinema.models.Movie;
import bg.tuvarna.springboot.Cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{pathname}/movies")
    public List<Movie> getAllMovies(@PathVariable String pathname) {
        return movieService.findAll();
    }

    @GetMapping("/{pathname}/movies/{id}")
    public Movie getmovie(@PathVariable String pathname, @PathVariable long id) {
        return movieService.findById(id);
    }

    @DeleteMapping("/{pathname}/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String pathname, @PathVariable long id) {

        movieService.deleteById(id);

        Movie movie = movieService.findById(id);

        if (movie == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String pathname, @PathVariable long id,
                                             @RequestBody Movie movie) {


        movie = movieService.createOrUpdateMovie(movie);

        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @PostMapping("/{pathname}/movies")
    public ResponseEntity<Void> createMovie(@PathVariable String pathname, @RequestBody Movie movie) {

        Movie checkMovie = movieService.findByTitle(movie.getTitle());

        if (checkMovie != null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        movie = movieService.createOrUpdateMovie(movie);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
