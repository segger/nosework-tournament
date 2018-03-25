package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.domain.*;
import se.johannalynn.nosework.noseworktournament.model.EventResult;
import se.johannalynn.nosework.noseworktournament.model.Result;
import se.johannalynn.nosework.noseworktournament.model.ResultType;

import java.util.*;

@Service
public class ResultService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ProtocolRepository protocolRepository;

    public List<? extends Result> getResults(long id, String type) {
        switch(ResultType.getByType(type)) {
            case EVENT:
                return getEventResults(id);
            case CONTEST:
            case TOURNAMENT:
                break;
        }
        throw new IllegalArgumentException();
    }

    private List<? extends Result> getEventResults(long id) {
        List<EventResult> resultList = new ArrayList<>();

        EventEntity event = eventRepository.findOne(id);
        Iterator<ProtocolEntity> itr = protocolRepository.findByEvent(event).iterator();

        while(itr.hasNext()) {
            ProtocolEntity protocol = itr.next();
            EventResult result = new EventResult();
            result.setParticipant(parseParticipant(protocol.getParticipant()));
            result.setPoints(protocol.getPoints());
            result.setErrorPoints(protocol.getErrorPoints());
            result.setSse(protocol.isSse());
            result.setTime(protocol.getTime());
            resultList.add(result);
        }

        Comparator<EventResult> resultComparator =
                Comparator.comparingInt(EventResult::getPoints).reversed()
                .thenComparing(EventResult::getErrorPoints)
                .thenComparing(EventResult::getTime)
                .thenComparingInt(EventResult::getErrorPoints);
        Collections.sort(resultList, resultComparator);

        PlacementCalculation calculation = new PlacementCalculation(10, resultList);
        return calculation.asEventResultList();
    }

    private String parseParticipant(ParticipantEntity participant) {
        if(participant == null) return "";
        return participant.getOwner() + " & " + participant.getDog();
    }
}
