package bg.tuvarna.springboot.Cinema.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class RatingController {
}
