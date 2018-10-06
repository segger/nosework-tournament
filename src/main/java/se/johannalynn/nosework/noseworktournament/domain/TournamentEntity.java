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

    @Column
    @Enumerated(EnumType.STRING)
    private EventLevel level;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContestEntity> contests;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tournaments")
    private Set<ParticipantEntity> participants;

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

    public EventLevel getLevel() {
        return level;
    }

    public void setLevel(EventLevel level) {
        this.level = level;
    }

    public Set<ContestEntity> getContests() {
        return contests;
    }

    public void setContests(Set<ContestEntity> contests) {
        this.contests = contests;
    }

    public Set<ParticipantEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<ParticipantEntity> participants) {
        this.participants = participants;
    }
}
