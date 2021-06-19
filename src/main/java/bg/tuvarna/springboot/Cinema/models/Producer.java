package bg.tuvarna.springboot.Cinema.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "producers")
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "producers_id_seq", sequenceName = "producers_id_seq")
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;

    public Producer() {
    }

    public Producer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Producer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
