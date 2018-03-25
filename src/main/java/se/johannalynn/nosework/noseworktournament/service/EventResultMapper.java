package se.johannalynn.nosework.noseworktournament.service;

import se.johannalynn.nosework.noseworktournament.domain.ParticipantEntity;
import se.johannalynn.nosework.noseworktournament.domain.ProtocolEntity;
import se.johannalynn.nosework.noseworktournament.model.EventResult;
import se.johannalynn.nosework.noseworktournament.model.Participant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventResultMapper {

    private List<EventResult> resultList;

    public EventResultMapper(List<ProtocolEntity> resultList, int maxPlacement) {
        List<EventResult> eventResults = new ArrayList<>();
        int tournamentPoints = maxPlacement + 10;
        int placement = 1;
        Iterator<ProtocolEntity> itr = resultList.iterator();
        while(itr.hasNext()) {
            ProtocolEntity protocol = itr.next();
            EventResult result = new EventResult();
            result.setPoints(protocol.getPoints());
            result.setErrorPoints(protocol.getErrorPoints());
            result.setSse(protocol.isSse());
            result.setTime(protocol.getTime());
            result.setParticipant(parseParticipant(protocol.getParticipant()));

            String sse = protocol.isSse() ? " + 5" : "";
            if (protocol.getPoints() > 0) {
                if(placement <= maxPlacement) {
                    result.setPlacement(""+placement++);
                    result.setTournamentPoints(tournamentPoints);
                    result.setTournamentPointsPresentation("" + tournamentPoints + sse);
                    tournamentPoints--;
                } else {
                    result.setPlacement("GK");
                    result.setTournamentPoints(10);
                    result.setTournamentPointsPresentation("10" + sse);
                }
            } else {
                result.setPlacement("IG");
                result.setTournamentPoints(3);
                result.setTournamentPointsPresentation("3" + sse);
            }

            eventResults.add(result);
        }

        this.resultList = eventResults;
    }

    private Participant parseParticipant(ParticipantEntity participant) {
        Participant participantModel = new Participant();
        if(participant == null) return participantModel;
        participantModel.setId(participant.getId());
        participantModel.setPresentation(participant.getOwner() + " & " + participant.getDog());
        return participantModel;
    }

    public List<EventResult> asEventResultList() {
        return resultList;
    }
}
