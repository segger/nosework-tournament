package se.johannalynn.nosework.noseworktournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantEntity;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private ParticipantRepository participantRepository;

    @Autowired
    public DataLoader(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public void run(ApplicationArguments args) {
        ParticipantEntity participant = new ParticipantEntity();
        participant.setOwner("Alpha");
        participant.setDog("Omega");
        participantRepository.save(participant);
    }
}
