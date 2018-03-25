package se.johannalynn.nosework.noseworktournament.model;

import java.sql.Time;

public class EventResult extends Result {
    private Time time;
    private int errorPoints;
    private boolean sse;
    private int tournamentPoints;
    private String displayTournamentPoints;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getErrorPoints() {
        return errorPoints;
    }

    public void setErrorPoints(int errorPoints) {
        this.errorPoints = errorPoints;
    }

    public boolean isSse() {
        return sse;
    }

    public void setSse(boolean sse) {
        this.sse = sse;
    }

    public int getTournamentPoints() {
        return tournamentPoints;
    }

    public void setTournamentPoints(int tournamentPoints) {
        this.tournamentPoints = tournamentPoints;
    }

    public String getDisplayTournamentPoints() {
        return displayTournamentPoints;
    }

    public void setDisplayTournamentPoints(String displayTournamentPoints) {
        this.displayTournamentPoints = displayTournamentPoints;
    }
}
