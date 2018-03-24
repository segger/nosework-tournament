package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tournaments", path = "tournaments")
public interface TournamentRepository extends CrudRepository<TournamentEntity, Long> {

}
