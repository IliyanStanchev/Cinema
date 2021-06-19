package bg.tuvarna.springboot.Cinema.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rows")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "hall"})
public class Row implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rows_id_seq", sequenceName = "rows_id_seq")
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    private int rowNumber;

    @OneToMany(mappedBy = "row", fetch = FetchType.EAGER)
    private List<Seat> seats;

    public Row() {
    }

    public Row(long id, Hall hall, int rowNumber) {
        this.id = id;
        this.hall = hall;
        this.rowNumber = rowNumber;
    }


    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return getId() == row.getId() && getRowNumber() == row.getRowNumber() && Objects.equals(getHall(), row.getHall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHall(), getRowNumber());
    }
}
