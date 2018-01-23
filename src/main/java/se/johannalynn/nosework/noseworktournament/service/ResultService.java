package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Event;
import se.johannalynn.nosework.noseworktournament.entity.Result;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
import se.johannalynn.nosework.noseworktournament.model.ContestResult;
import se.johannalynn.nosework.noseworktournament.model.EventResult;
import se.johannalynn.nosework.noseworktournament.model.TournamentResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    public List<TournamentResult> getTournamentResult(Tournament tournament) {
        return new ArrayList<>();
    }

    public List<ContestResult> getContestResult(Contest contest) {
        return new ArrayList<>();
    }

    public List<EventResult> getEventResult(Event event) {
        List<EventResult> resultList = new ArrayList<>();
        for(Result result : event.getResults()) {
            EventResult eventResult = new EventResult();
            eventResult.setTime(result.getTime());
            eventResult.setSse(result.getSse());
            resultList.add(eventResult);
        }
        return resultList;
    }
}
