package bg.tuvarna.springboot.Cinema.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tickets_id_seq", sequenceName = "tickets_id_seq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private double price;

    public Ticket() {
    }

    public Ticket(long id, User user, Showtime showtime, Seat seat, double price) {
        this.id = id;
        this.user = user;
        this.showtime = showtime;
        this.seat = seat;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getId() == ticket.getId() && Double.compare(ticket.getPrice(), getPrice()) == 0 && Objects.equals(getUser(), ticket.getUser()) && Objects.equals(getShowtime(), ticket.getShowtime()) && Objects.equals(getSeat(), ticket.getSeat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getShowtime(), getSeat(), getPrice());
    }
}
