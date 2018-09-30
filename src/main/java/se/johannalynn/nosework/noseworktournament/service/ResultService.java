package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.domain.*;
import se.johannalynn.nosework.noseworktournament.model.EventResult;
import se.johannalynn.nosework.noseworktournament.model.Result;
import se.johannalynn.nosework.noseworktournament.model.ResultType;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    TournamentRepository tournamentRepository;

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
                return getTournamentResults(id);
        }
        throw new IllegalArgumentException();
    }

    // cache?
    private List<EventResult> getEventResults(long id) {
        EventEntity event = eventRepository.findOne(id);
        List<ProtocolEntity> protocolList = protocolRepository.findByEvent(event);
        protocolList = sort(protocolList);
        EventResultMapper mapper = new EventResultMapper(protocolList, 10);
        return mapper.asEventResultList();
    }

    private List<ProtocolEntity> sort(List<ProtocolEntity> resultList) {
        Comparator<ProtocolEntity> resultComparator =
                Comparator.comparingInt(ProtocolEntity::getPoints).reversed()
                .thenComparing(ProtocolEntity::getErrorPoints)
                .thenComparing(ProtocolEntity::getTime)
                .thenComparing(ProtocolEntity::getErrorPoints);
        Collections.sort(resultList, resultComparator);

        return resultList;
    }

    private List<? extends Result> getTournamentResults(long id) {
        List<Result> tournamentResultList = new ArrayList<>();
        Map<Long, List<EventResult>> eventResultsByParticipant = new HashMap<>();
        TournamentEntity tournament = tournamentRepository.findOne(id);
        Iterator<ContestEntity> contestItr = tournament.getContests().iterator();
        while(contestItr.hasNext()) {
            ContestEntity contest = contestItr.next();
            Iterator<EventEntity> eventItr = contest.getEvents().iterator();
            while(eventItr.hasNext()) {
                EventEntity event = eventItr.next();
                List<EventResult> eventResults = getEventResults(event.getId());
                for(EventResult eventResult : eventResults) {
                    long participantId = eventResult.getParticipant().getId();
                    List<EventResult> participantResults = eventResultsByParticipant.get(participantId);
                    if(participantResults == null) participantResults = new ArrayList<>();
                    participantResults.add(eventResult);
                    eventResultsByParticipant.put(participantId, participantResults);
                }
            }
        }

        for(Map.Entry<Long, List<EventResult>> entry : eventResultsByParticipant.entrySet()) {
            EventResult er = entry.getValue().get(0);
            Result tournamentResult = new Result();
            tournamentResult.setId(er.hashCode());
            tournamentResult.setParticipant(er.getParticipant());
            int tournamentPoints = entry.getValue().stream().mapToInt(EventResult::getTournamentPoints).sum();
            tournamentResult.setPoints(tournamentPoints);
            tournamentResultList.add(tournamentResult);
        }

        Collections.sort(tournamentResultList, Comparator.comparingInt(Result::getPoints).reversed());
        int placement = 1;
        for(Result tournamentResult : tournamentResultList) {
            tournamentResult.setPlacement(""+placement++);
        }
        return tournamentResultList;
    }
}
