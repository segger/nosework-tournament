package se.johannalynn.nosework.noseworktournament.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.johannalynn.nosework.noseworktournament.domain.ResultEntity;
import se.johannalynn.nosework.noseworktournament.domain.ResultRepository;
import se.johannalynn.nosework.noseworktournament.model.Result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    public List<Result> getResults() {
        List<Result> resultList = new ArrayList<>();
        Iterator<ResultEntity> itr = resultRepository.findAll().iterator();
        while(itr.hasNext()) {
            ResultEntity entity = itr.next();
            Result result = new Result();
            result.setParticipant(entity.getParticipant());
            resultList.add(result);
        }
        return resultList;
    }
}
