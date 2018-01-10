package se.johannalynn.nosework.noseworktournament.entity;

import javax.persistence.*;
import java.sql.Date;

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
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column
    private Date maxTime;

    //points
    //results

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "contestId")
    //private Contest contests;

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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }
}
