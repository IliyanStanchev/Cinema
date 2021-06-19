package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s FROM Seat s WHERE s.row.hall.id = ?1")
    List<Seat> getSeatsByHall(long id);
}
