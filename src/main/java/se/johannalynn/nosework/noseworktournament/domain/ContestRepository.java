package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;

import java.util.List;

public interface ContestRepository extends CrudRepository<Contest, Long> {
    List<Contest> findAllByTournament(Tournament tournament);
}
