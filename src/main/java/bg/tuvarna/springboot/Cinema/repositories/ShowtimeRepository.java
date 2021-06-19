package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Hall;
import bg.tuvarna.springboot.Cinema.models.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Query("SELECT s FROM Showtime s WHERE s.startTime BETWEEN ?1 AND ?2 AND s.hall = ?3")
    Showtime checkShowtime(Date startTime, Date endTime, Hall hall);

    @Query("SELECT s FROM Showtime s WHERE s.startTime BETWEEN ?1 AND ?2")
    List<Showtime> findAllByDate(Date date, Date endDate);
}
