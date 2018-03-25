package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "participant")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String owner;

    @Column
    private String dog;

    /*
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "participant")
    private Set<TournamentEntity> tournaments; */


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tournament_participant",
            joinColumns = { @JoinColumn(name = "tournament") },
            inverseJoinColumns = { @JoinColumn(name = "participant") })
    private Set<TournamentEntity> tournaments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDog() {
        return dog;
    }

    public void setDog(String dog) {
        this.dog = dog;
    }

    public Set<TournamentEntity> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<TournamentEntity> tournaments) {
        this.tournaments = tournaments;
    }
}
