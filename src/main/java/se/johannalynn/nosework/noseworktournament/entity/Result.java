package se.johannalynn.nosework.noseworktournament.entity;

import com.fasterxml.jackson.annotation.*;
import se.johannalynn.nosework.noseworktournament.util.PointConverter;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String participant;

    @Column
    private Time time;

    @Column
    private Boolean sse;

    @Column
    private String stashesFound;

    @Column
    private int errorOnePoints;

    @Column
    private int errorTwoPoints;

    @Column
    private int errorThreePoints;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Boolean getSse() {
        return sse;
    }

    public void setSse(Boolean sse) {
        this.sse = sse;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStashesFound() {
        return stashesFound;
    }

    public void setStashesFound(String stashesFound) {
        this.stashesFound = stashesFound;
    }

    public int getErrorOnePoints() {
        return errorOnePoints;
    }

    public void setErrorOnePoints(int errorOnePoints) {
        this.errorOnePoints = errorOnePoints;
    }

    public int getErrorTwoPoints() {
        return errorTwoPoints;
    }

    public void setErrorTwoPoints(int errorTwoPoints) {
        this.errorTwoPoints = errorTwoPoints;
    }

    public int getErrorThreePoints() {
        return errorThreePoints;
    }

    public void setErrorThreePoints(int errorThreePoints) {
        this.errorThreePoints = errorThreePoints;
    }
}
