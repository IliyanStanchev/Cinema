package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.showtime.id = ?1")
    ArrayList<Ticket> findAllByShowtime(long id);
}
