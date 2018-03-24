package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContestEntity> contests;

    /*
    @ManyToMany
    private Set<ParticipantEntity> participants;*/

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

    public Set<ContestEntity> getContests() {
        return contests;
    }

    public void setContests(Set<ContestEntity> contests) {
        this.contests = contests;
    }
}
