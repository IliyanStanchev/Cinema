package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.User;
import bg.tuvarna.springboot.Cinema.services.RoleService;
import bg.tuvarna.springboot.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("{pathname}/users")
    public List<User> getAllUsers(@PathVariable String pathname) {
        return userService.findAll();
    }

    @GetMapping("/{pathname}/users/{id}")
    public User getUser(@PathVariable String pathname, @PathVariable long id) {

        return userService.findById(id);
    }

    @DeleteMapping("/{pathname}/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String pathname, @PathVariable long id) {

        userService.deleteById(id);

        User producers = userService.findById(id);

        if (producers == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pathname}/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String pathname, @PathVariable long id,
                                           @RequestBody User producer) {

        User updatedProducer = userService.createOrUpdateUser(producer);

        return new ResponseEntity<User>(producer, HttpStatus.OK);
    }

    @PostMapping("/{pathname}/login")
    public ResponseEntity<Void> createUser(@PathVariable String pathname, @RequestBody User requestUser) {

        User user = userService.getByUsername(requestUser.getUsername());
        if (user != null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        user = userService.getByEmail(requestUser.getEmail());
        if (user != null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        requestUser = userService.createOrUpdateUser(requestUser);

        if (requestUser == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("{pathname}/admin/profile")
    public User authenticateUser(@PathVariable String pathname) {

        User user = userService.getAdmin();

        if (user != null)
            return user;

        return null;
    }
}