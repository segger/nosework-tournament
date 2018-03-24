package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column
    @Enumerated(EnumType.STRING)
    private EventLevel level;

    @Column
    private Time maxTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest")
    private ContestEntity contest;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StashEntity> stashes;

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

    public EventLevel getLevel() {
        return level;
    }

    public void setLevel(EventLevel level) {
        this.level = level;
    }

    public Time getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Time maxTime) {
        this.maxTime = maxTime;
    }

    public ContestEntity getContest() {
        return contest;
    }

    public void setContest(ContestEntity contest) {
        this.contest = contest;
    }

    public Set<StashEntity> getStashes() {
        return stashes;
    }

    public void setStashes(Set<StashEntity> stashes) {
        this.stashes = stashes;
    }
}
