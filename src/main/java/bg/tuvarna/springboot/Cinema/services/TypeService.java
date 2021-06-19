package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Type;
import bg.tuvarna.springboot.Cinema.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> findAll() {
        List<Type> types = (List<Type>) typeRepository.findAll();

        if (types.size() > 0) {
            return types;
        } else {
            return new ArrayList<Type>();
        }
    }

    public Type findById(long id) {
        Optional<Type> typeOptional = typeRepository.findById(id);

        if (typeOptional.isPresent()) {
            return typeOptional.get();
        } else {
            return null;
        }
    }

    public Type createOrUpdateType(Type type) {

        Optional<Type> typeOptional = typeRepository.findById(type.getId());

        if (typeOptional.isPresent()) {
            Type newEntity = typeOptional.get();

            newEntity.setType(type.getType());

            newEntity = typeRepository.save(newEntity);

            return newEntity;
        } else {
            typeRepository.save(type);

            return type;
        }
    }

    public void deleteById(Long id) {
        Optional<Type> type = typeRepository.findById(id);

        if (type.isPresent()) {
            typeRepository.deleteById(id);
        }
    }

    public Type findByType(String type) {

        return typeRepository.findByType(type);
    }
}
