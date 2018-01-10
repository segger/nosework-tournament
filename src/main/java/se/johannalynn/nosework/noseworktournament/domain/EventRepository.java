package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Event;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByContestId(Long contestId);
}
