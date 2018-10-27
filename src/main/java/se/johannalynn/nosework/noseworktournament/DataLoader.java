package se.johannalynn.nosework.noseworktournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.johannalynn.nosework.noseworktournament.domain.*;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private ProtocolRepository protocolRepository;

    public void run(ApplicationArguments args) throws ParseException {
        TournamentEntity tournament2 = new TournamentEntity();
        tournament2.setName("Spöksniffen");
        tournament2.setLevel(EventLevel.ONE);

        TournamentEntity tournament = new TournamentEntity();
        tournament.setName("Adventssniffen");
        tournament.setLevel(EventLevel.ONE);

        Set<ContestEntity> contests = new HashSet<>();
        ContestEntity contest = new ContestEntity();
        contest.setName("Första advent");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date contestDate = new Date(dateFormat.parse("2018-01-01").getTime());
        contest.setDate(contestDate);
        contest.setTournament(tournament);

        Set<EventEntity> events = new HashSet<>();
        EventEntity event = new EventEntity();
        event.setContest(contest);
        event.setName("En stor bil");
        event.setContestOrder(1);
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        Time maxTime = new Time(timeFormat.parse("01:30").getTime());
        event.setMaxTime(maxTime);
        event.setType(EventType.CONTAINERS);

        EventEntity event2 = new EventEntity();
        event2.setContest(contest);
        event2.setName("Tre granar");
        event2.setContestOrder(2);
        Time maxTime2 = new Time(timeFormat.parse("02:30").getTime());
        event2.setMaxTime(maxTime2);
        event2.setType(EventType.VEHICLES);

        Set<StashEntity> stashes = new HashSet<>();
        StashEntity stash = new StashEntity();
        stash.setEvent(event);
        stash.setName("Bakom elementet");
        stash.setPoints(25);
        stashes.add(stash);

        Set<StashEntity> stashes2 = new HashSet<>();
        StashEntity stash2 = new StashEntity();
        stash2.setEvent(event2);
        stash2.setName("Fönster");
        stash2.setPoints(25);
        stashes2.add(stash2);

        event.setStashes(stashes);
        event2.setStashes(stashes2);
        events.add(event);
        events.add(event2);
        contest.setEvents(events);
        contests.add(contest);
        tournament.setContests(contests);

        ParticipantEntity participant = createParticipant("Alpha","Omega", tournament);
        ParticipantEntity participant2 = createParticipant("Musse","Pluto", tournament);
        ParticipantEntity participant3 = createParticipant("Piff","Puff", tournament);
        ParticipantEntity participant4 = createParticipant("Puff","Piff", tournament);

        Set<ParticipantEntity> tournamentParticipants = tournament.getParticipants();
        if(tournamentParticipants == null) tournamentParticipants = new HashSet<>();
        tournamentParticipants.add(participant);
        tournamentParticipants.add(participant2);
        tournamentParticipants.add(participant3);
        tournamentParticipants.add(participant4);
        tournament.setParticipants(tournamentParticipants);

        ProtocolEntity protocol = createProtocolEntity(participant, event, 25, false,
                0, new Time(timeFormat.parse("00:25").getTime()));
        ProtocolEntity protocol2 = createProtocolEntity(participant2, event, 25, false,
                0, new Time(timeFormat.parse("00:20").getTime()));
        ProtocolEntity protocol3 = createProtocolEntity(participant3, event, 25, false,
                2, new Time(timeFormat.parse("00:11").getTime()));
        ProtocolEntity protocol4 = createProtocolEntity(participant4, event, 0, true,
                0, maxTime);

        participantRepository.save(participant);
        participantRepository.save(participant2);
        participantRepository.save(participant3);
        participantRepository.save(participant4);

        protocolRepository.save(protocol);
        protocolRepository.save(protocol2);
        protocolRepository.save(protocol3);
        protocolRepository.save(protocol4);

        tournamentRepository.save(tournament);

        tournamentRepository.save(tournament2);
    }

    private ParticipantEntity createParticipant(String owner, String dog, TournamentEntity tournament) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setDog(dog);
        participantEntity.setOwner(owner);
        Set<TournamentEntity> tournaments = new HashSet<>();
        tournaments.add(tournament);
        participantEntity.setTournaments(tournaments);
        return participantEntity;
    }

    private ProtocolEntity createProtocolEntity(ParticipantEntity participant,
                                                EventEntity event,
                                                int points, boolean sse, int errorPoints,
                                                Time time) {
        ProtocolEntity protocolEntity = new ProtocolEntity();
        protocolEntity.setErrorPoints(errorPoints);
        protocolEntity.setEvent(event);
        protocolEntity.setParticipant(participant);
        protocolEntity.setPoints(points);
        protocolEntity.setSse(sse);
        protocolEntity.setTime(time);
        return protocolEntity;
    }
}
