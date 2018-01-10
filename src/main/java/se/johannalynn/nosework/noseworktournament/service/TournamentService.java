package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Participant;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;

@Service
public class TournamentService {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    private Tournament getTournament() {
        for(Tournament tournament : tournamentRepository.findAll()) {
            return tournament;
        }
        return null;
    }

    public void saveParticipant(Participant participant) {
        participant.setTournament(getTournament());
        participantRepository.save(participant);
    }

    public void saveContest(Contest contest) {
        contest.setTournament(getTournament());
        contestRepository.save(contest);
    }
}
