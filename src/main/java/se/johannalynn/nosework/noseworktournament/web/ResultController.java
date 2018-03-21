package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.johannalynn.nosework.noseworktournament.domain.ResultRepository;
import se.johannalynn.nosework.noseworktournament.model.Result;
import se.johannalynn.nosework.noseworktournament.service.ResultService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResultController {

    @Autowired
    ResultService resultService;

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public List<Result> getResults() {
        return resultService.getResults();
    }
}
