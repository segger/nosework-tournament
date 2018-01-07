package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.model.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    Tournament findByName(String name);
}
