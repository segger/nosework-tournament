package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "protocols", path = "protocols")
public interface ProtocolRepository extends CrudRepository<ProtocolEntity, Long> {

    List<ProtocolEntity> findByEvent(EventEntity event);

}
