package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Producer;
import bg.tuvarna.springboot.Cinema.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public List<Producer> findAll() {
        List<Producer> producersList = (List<Producer>) producerRepository.findAll();

        if (producersList.size() > 0) {
            return producersList;
        } else {
            return new ArrayList<Producer>();
        }
    }

    public Producer findById(long id) {
        Optional<Producer> producer = producerRepository.findById(id);

        if (producer.isPresent()) {
            return producer.get();
        } else {
            return null;
        }
    }

    public Producer createOrUpdateProducer(Producer entity) {

        Optional<Producer> employee = producerRepository.findById(entity.getId());

        if (employee.isPresent()) {
            Producer newEntity = employee.get();

            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = producerRepository.save(newEntity);

            return newEntity;
        } else {
            producerRepository.save(entity);

            return entity;
        }
    }

    public void deleteById(Long id) {
        Optional<Producer> producer = producerRepository.findById(id);

        if (producer.isPresent()) {
            producerRepository.deleteById(id);
        }
    }


}
