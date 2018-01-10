package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}
