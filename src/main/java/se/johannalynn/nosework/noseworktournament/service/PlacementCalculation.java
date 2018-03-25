package se.johannalynn.nosework.noseworktournament.service;

import se.johannalynn.nosework.noseworktournament.model.EventResult;

import java.util.Iterator;
import java.util.List;

public class PlacementCalculation {

    private List<EventResult> resultList;

    public PlacementCalculation(int maxPlacement, List<EventResult> resultList) {
        int tournamentPoints = maxPlacement + 10;
        int placement = 1;
        Iterator<EventResult> resItr = resultList.iterator();
        while(resItr.hasNext()) {
            EventResult res = resItr.next();
            String sse = res.isSse() ? " + 5" : "";
            if (res.getPoints() > 0) {
                if(placement <= maxPlacement) {
                    res.setPlacement(""+placement++);
                    res.setTournamentPoints(tournamentPoints);
                    res.setDisplayTournamentPoints("" + tournamentPoints + sse);
                    tournamentPoints--;
                } else {
                    res.setPlacement("GK");
                    res.setTournamentPoints(10);
                    res.setDisplayTournamentPoints("10" + sse);
                }
            } else {
                res.setPlacement("IG");
                res.setTournamentPoints(3);
                res.setDisplayTournamentPoints("3" + sse);
            }
        }

        this.resultList = resultList;
    }

    public List<EventResult> asEventResultList() {
        return resultList;
    }
}
