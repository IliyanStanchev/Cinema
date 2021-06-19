package bg.tuvarna.springboot.Cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "seats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "row"})
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seats_id_seq", sequenceName = "seats_id_seq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private Row row;

    private int seatNumber;

    public Seat() {
    }

    public Seat(long id, Row row, int seatNumber) {
        this.id = id;
        this.row = row;
        this.seatNumber = seatNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seat)) return false;
        Seat seat = (Seat) o;
        return getId() == seat.getId() && getSeatNumber() == seat.getSeatNumber() && Objects.equals(getRow(), seat.getRow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRow(), getSeatNumber());
    }
}
