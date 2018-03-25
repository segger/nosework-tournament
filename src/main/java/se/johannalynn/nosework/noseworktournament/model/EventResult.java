package se.johannalynn.nosework.noseworktournament.model;

import java.sql.Time;

public class EventResult extends Result {
    private Time time;
    private int errorPoints;
    private boolean sse;
    private int tournamentPoints;
    private String tournamentPointsPresentation;

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

    public String getTournamentPointsPresentation() {
        return tournamentPointsPresentation;
    }

    public void setTournamentPointsPresentation(String tournamentPointsPresentation) {
        this.tournamentPointsPresentation = tournamentPointsPresentation;
    }
}
