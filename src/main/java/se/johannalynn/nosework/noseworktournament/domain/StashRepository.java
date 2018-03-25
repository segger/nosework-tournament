package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "stashes", path = "stashes")
public interface StashRepository extends CrudRepository<StashEntity, Long> {

}
