package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Movie;
import bg.tuvarna.springboot.Cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();

        if (movies.size() > 0) {
            return movies;
        } else {
            return new ArrayList<Movie>();
        }
    }

    public Movie findById(long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            return movie.get();
        } else {
            return null;
        }
    }

    public Movie createOrUpdateMovie(Movie movie) {

        Optional<Movie> movieOptional = movieRepository.findById(movie.getId());

        if (movieOptional.isPresent()) {
            Movie newEntity = movieOptional.get();

            newEntity.setTitle(movie.getTitle());
            newEntity.setGenre(movie.getGenre());
            newEntity.setProducer(movie.getProducer());
            newEntity.setReview(movie.getReview());
            newEntity.setRating(movie.getRating());
            newEntity.setImageUrl(movie.getImageUrl());

            newEntity = movieRepository.save(newEntity);

            return newEntity;
        } else {
            movieRepository.save(movie);

            return movie;
        }
    }

    public void deleteById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            movieRepository.deleteById(id);
        }
    }

    public Movie findByTitle(String title) {

        return movieRepository.findByTitle(title);
    }
}
