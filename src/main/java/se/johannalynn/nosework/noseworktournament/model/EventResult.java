package se.johannalynn.nosework.noseworktournament.model;

import java.sql.Time;

public class EventResult extends Result {
    private Time time;
    private Boolean sse;

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
