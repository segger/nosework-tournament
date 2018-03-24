package se.johannalynn.nosework.noseworktournament.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "result", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"participant", "event"})
})
public class ResultEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private Time time;

    @Column
    private boolean sse;

    @Column
    private int errorPoints;

    @Column
    private int points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant")
    private ParticipantEntity participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event")
    private EventEntity event;

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

    public boolean isSse() {
        return sse;
    }

    public void setSse(boolean sse) {
        this.sse = sse;
    }

    public int getErrorPoints() {
        return errorPoints;
    }

    public void setErrorPoints(int errorPoints) {
        this.errorPoints = errorPoints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ParticipantEntity getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantEntity participant) {
        this.participant = participant;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
