package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.model.Result;
import se.johannalynn.nosework.noseworktournament.service.ResultService;

import java.util.List;

@RestController
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping("/results/{id}")
    public List<? extends Result> getResults(@PathVariable("id") final long id,
                                   @RequestParam("type") final String type) {
        return resultService.getResults(id, type);
    }
}
