package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "participants", path = "participants")
public interface ParticipantRepository extends CrudRepository<ParticipantEntity, Long> {

}
