package bg.tuvarna.springboot.Cinema.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ratings")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ratings_id_seq", sequenceName = "ratings_id_seq")
    @Column(name = "id")
    private long id;

    private int ratingSum;
    private int ratingCount;
    private double rating;

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addRating(int addedRating) {
        ratingCount++;
        ratingSum += addedRating;

        rating = ratingSum / ratingCount;
    }

}
