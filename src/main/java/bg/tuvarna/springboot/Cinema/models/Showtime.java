package bg.tuvarna.springboot.Cinema.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "showtimes")
public class Showtime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "showtimes_id_seq", sequenceName = "showtimes_id_seq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Date startTime;

    private Date endTime;

    public Showtime(long id, Movie movie, Date startTime, Date endTime, Type type, Hall hall) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.hall = hall;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @OneToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Showtime() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showtime)) return false;
        Showtime showtime = (Showtime) o;
        return getId() == showtime.getId() && Objects.equals(getMovie(), showtime.getMovie()) && Objects.equals(getStartTime(), showtime.getStartTime()) && Objects.equals(getEndTime(), showtime.getEndTime()) && Objects.equals(getType(), showtime.getType()) && Objects.equals(getHall(), showtime.getHall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMovie(), getStartTime(), getEndTime(), getType(), getHall());
    }
}
