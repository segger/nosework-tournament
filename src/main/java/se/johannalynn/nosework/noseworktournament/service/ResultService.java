package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.entity.*;
import se.johannalynn.nosework.noseworktournament.model.ContestResult;
import se.johannalynn.nosework.noseworktournament.model.EventResult;
import se.johannalynn.nosework.noseworktournament.model.TournamentResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            eventResult.setId(result.getId());
            eventResult.setParticipant(result.getParticipant());
            eventResult.setTime(result.getTime());
            eventResult.setSse(result.getSse());
            int totError = result.getErrorOnePoints() + result.getErrorTwoPoints() + result.getErrorThreePoints();
            eventResult.setError(totError);
            int points = findTotPoints(result.getStashesFound(), event.getStashPoints());
            eventResult.setPoints(points);
            resultList.add(eventResult);
        }
        return resultList;
    }

    private int findTotPoints(String stashesFound, List<StashPoint> stashes) {
        if(stashesFound == null) return 0;
        int totPoints = 0;
        List<String> stashesFoundList = new ArrayList<>(Arrays.asList(stashesFound.split(",")));
        //TODO: stream
        for(String found : stashesFoundList) {
            for(StashPoint point : stashes) {
                if(found.equalsIgnoreCase(point.getStashName())) {
                    totPoints += point.getPoints();
                }
            }
        }
        return totPoints;
    }
}
