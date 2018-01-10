package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;

public interface ContestRepository extends CrudRepository<Contest, Long> {
}
