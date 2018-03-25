package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "contest")
public class ContestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Date date;

    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventEntity> events;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament")
    private TournamentEntity tournament;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    public Set<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }
}
