package se.johannalynn.nosework.noseworktournament.model;

import se.johannalynn.nosework.noseworktournament.entity.Participant;

public abstract class Result {
    private String participant;
    private int totalPoints;
    private String placement;

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}