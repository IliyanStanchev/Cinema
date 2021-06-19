package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RowRepository extends JpaRepository<Row, Long> {

    @Query("SELECT r FROM Row r WHERE r.hall.id = ?1")
    List<Row> getHallRows(long id);
}
