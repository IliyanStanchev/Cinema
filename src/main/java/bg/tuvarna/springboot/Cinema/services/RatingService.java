package bg.tuvarna.springboot.Cinema.services;

import bg.tuvarna.springboot.Cinema.models.Rating;
import bg.tuvarna.springboot.Cinema.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> findAll() {
        List<Rating> ratings = (List<Rating>) ratingRepository.findAll();

        if (ratings.size() > 0) {
            return ratings;
        } else {
            return new ArrayList<Rating>();
        }
    }

    public Rating findById(long id) {
        Optional<Rating> ratingOptional = ratingRepository.findById(id);

        if (ratingOptional.isPresent()) {
            return ratingOptional.get();
        } else {
            return null;
        }
    }

    public Rating createOrUpdateRating(Rating rating) {

        Optional<Rating> RatingOptional = ratingRepository.findById(rating.getId());

        if (RatingOptional.isPresent()) {
            Rating newEntity = RatingOptional.get();

            newEntity.setRatingCount(rating.getRatingCount());
            newEntity.setRatingSum(rating.getRatingSum());
            newEntity.setRating(rating.getRating());

            newEntity = ratingRepository.save(newEntity);

            return newEntity;
        } else {
            ratingRepository.save(rating);

            return rating;
        }
    }

    public void deleteById(Long id) {
        Optional<Rating> rating = ratingRepository.findById(id);

        if (rating.isPresent()) {
            ratingRepository.deleteById(id);
        }
    }
}
