package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.domain.*;
import se.johannalynn.nosework.noseworktournament.entity.*;

@Service
public class TournamentService {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findOne(tournamentId);
    }

    public Tournament getTournamentByContestId(Long contestId) {
        Contest contest = contestRepository.findOne(contestId);
        return contest.getTournament();
    }

    public void saveParticipant(Long tournamentId, Participant participant) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        participant.setTournament(tournament);
        participantRepository.save(participant);
    }

    public void saveContest(Long tournamentId, Contest contest) {
        Tournament tournament = tournamentRepository.findOne(tournamentId);
        contest.setTournament(tournament);
        contestRepository.save(contest);
    }

    public void saveEvent(Long contestId, Event event) {
        Contest contest = contestRepository.findOne(contestId);
        event.setContest(contest);
        eventRepository.save(event);
    }
}
