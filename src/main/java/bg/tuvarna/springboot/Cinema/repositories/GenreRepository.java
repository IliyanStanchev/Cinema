package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.name = ?1")
    Genre findByGenre(String name);
}
