package se.johannalynn.nosework.noseworktournament.domain;

import org.springframework.data.repository.CrudRepository;
import se.johannalynn.nosework.noseworktournament.entity.Event;
import se.johannalynn.nosework.noseworktournament.entity.Result;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, Long> {

}
