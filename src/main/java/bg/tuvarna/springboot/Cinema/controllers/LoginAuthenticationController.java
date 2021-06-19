package bg.tuvarna.springboot.Cinema.controllers;

import bg.tuvarna.springboot.Cinema.models.User;
import bg.tuvarna.springboot.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class LoginAuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("{pathname}/mainPage")
    public User authenticateUser(@PathVariable String pathname, @RequestBody User user) {
        User newUser = userService.authenticateLogin(user.getUsername());

        if (newUser == null)
            return null;

        if (newUser.getPassword().equals(user.getPassword())) {
            newUser.setLastLogin(new Date());
            User returnUser = userService.createOrUpdateUser(newUser);
            return returnUser;
        }

        return null;
    }
}
