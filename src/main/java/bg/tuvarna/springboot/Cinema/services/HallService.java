package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Hall;
import bg.tuvarna.springboot.Cinema.repositories.HallRepository;
import bg.tuvarna.springboot.Cinema.repositories.RowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private RowRepository rowRepository;

    public List<Hall> findAll() {
        List<Hall> hallsList = (List<Hall>) hallRepository.findAll();

        if (hallsList.size() > 0) {
            return hallsList;
        } else {
            return new ArrayList<Hall>();
        }
    }

    public Hall findById(long id) {
        Optional<Hall> hall = hallRepository.findById(id);

        if (hall.isPresent()) {
            return hall.get();
        } else {
            return null;
        }
    }

    public Hall createOrUpdateHall(Hall hall) {

        Optional<Hall> hallOptional = hallRepository.findById(hall.getId());

        if (hallOptional.isPresent()) {
            Hall newEntity = hallOptional.get();

            newEntity.setName(hall.getName());

            newEntity = hallRepository.save(newEntity);

            return newEntity;
        } else {
            hallRepository.save(hall);

            return hall;
        }
    }

    public void deleteById(Long id) {
        Optional<Hall> hall = hallRepository.findById(id);

        if (hall.isPresent()) {
            hallRepository.deleteById(id);
        }
    }

}
