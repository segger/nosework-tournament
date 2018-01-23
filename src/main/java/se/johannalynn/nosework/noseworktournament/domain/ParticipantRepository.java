package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Participant;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;

import java.util.List;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    List<Participant> findAllByTournament(Tournament tournament);
}
