package bg.tuvarna.springboot.Cinema.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "halls")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rows"})
public class Hall implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "halls_id_seq", sequenceName = "halls_id_seq")
    @Column(name = "id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Row> rows;

    public Hall() {
    }

    public Hall(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        Hall hall = (Hall) o;
        return getId() == hall.getId() && Objects.equals(getName(), hall.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
