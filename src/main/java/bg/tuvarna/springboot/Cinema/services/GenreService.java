package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Genre;
import bg.tuvarna.springboot.Cinema.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        List<Genre> genresList = (List<Genre>) genreRepository.findAll();

        if (genresList.size() > 0) {
            return genresList;
        } else {
            return new ArrayList<Genre>();
        }
    }

    public Genre findById(long id) {
        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isPresent()) {
            return genre.get();
        } else {
            return null;
        }
    }

    public Genre createOrUpdateGenre(Genre genre) {

        Optional<Genre> genreOptional = genreRepository.findById(genre.getId());

        if (genreOptional.isPresent()) {
            Genre newEntity = genreOptional.get();

            newEntity.setName(genre.getName());

            newEntity = genreRepository.save(newEntity);

            return newEntity;
        } else {
            genreRepository.save(genre);

            return genre;
        }
    }

    public void deleteById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);

        if (genre.isPresent()) {
            genreRepository.deleteById(id);
        }
    }

    public Genre findByGenre(String name) {
        return genreRepository.findByGenre(name);
    }
}
