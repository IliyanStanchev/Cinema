package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Role;
import bg.tuvarna.springboot.Cinema.models.User;
import bg.tuvarna.springboot.Cinema.repositories.RoleRepository;
import bg.tuvarna.springboot.Cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        List<User> producersList = (List<User>) userRepository.getCustomers();

        if (producersList.size() > 0) {
            return producersList;
        } else {
            return new ArrayList<User>();
        }
    }

    public User findById(long id) {
        Optional<User> producer = userRepository.findById(id);

        if (producer.isPresent()) {
            return producer.get();
        } else {
            return null;
        }
    }

    public User createOrUpdateUser(User entity) {

        Optional<User> employee = userRepository.findById(entity.getId());

        if (employee.isPresent()) {
            User newEntity = employee.get();

            newEntity.setUsername(entity.getUsername());
            newEntity.setPassword(entity.getPassword());
            newEntity.setEmail(entity.getEmail());

            newEntity = userRepository.save(newEntity);

            return newEntity;
        }

        Role role = roleRepository.getByRoleName("Customer");
        if (role == null)
            return null;

        entity.setRole(role);
        userRepository.save(entity);

        return entity;
    }

    public void deleteById(Long id) {
        Optional<User> producer = userRepository.findById(id);

        if (producer.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public User authenticateLogin(String username) {
        return userRepository.getByUsername(username);

    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    public User getAdmin() {
        return userRepository.getAdmin();
    }
}

