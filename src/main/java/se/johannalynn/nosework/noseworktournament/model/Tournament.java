package se.johannalynn.nosework.noseworktournament.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    //@OneToMany(mappedBy = "id")
    //private List<Contest> contests;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Participant> participants;

    public Tournament() {
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

    public void setId(Long id) {
        this.id = id;
    }
/*
    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }
*/
    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
