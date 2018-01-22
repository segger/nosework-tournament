package se.johannalynn.nosework.noseworktournament.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column
    private Time maxTime;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StashPoint> stashPoints;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    public Event() {
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Time getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Time maxTime) {
        this.maxTime = maxTime;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<StashPoint> getStashPoints() {
        return stashPoints;
    }

    public void setStashPoints(List<StashPoint> stashPoints) {
        this.stashPoints = stashPoints;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
