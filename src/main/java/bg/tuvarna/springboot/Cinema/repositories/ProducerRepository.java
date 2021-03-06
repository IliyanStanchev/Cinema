package bg.tuvarna.springboot.Cinema.repositories;

import bg.tuvarna.springboot.Cinema.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
