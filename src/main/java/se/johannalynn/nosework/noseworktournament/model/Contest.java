package se.johannalynn.nosework.noseworktournament.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    //@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    //private List<Event> events;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "tournamentId")
    //private Tournament tournament;

    public Contest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
