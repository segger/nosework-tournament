package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "result")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String participant;

    @Column
    private Time time;

    @Column
    private Boolean sse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
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
}
