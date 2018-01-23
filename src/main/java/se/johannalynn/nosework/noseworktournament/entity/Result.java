package se.johannalynn.nosework.noseworktournament.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Time;

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

    //Poäng
    //Felpoäng

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
}
