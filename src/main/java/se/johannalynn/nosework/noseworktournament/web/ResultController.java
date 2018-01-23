package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.domain.EventRepository;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.domain.ResultRepository;
import se.johannalynn.nosework.noseworktournament.entity.*;
import se.johannalynn.nosework.noseworktournament.model.ContestResult;
import se.johannalynn.nosework.noseworktournament.model.TournamentResult;
import se.johannalynn.nosework.noseworktournament.service.ResultService;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    ResultService resultService;

    @GetMapping(value = {"","/*"})
    public String getTournamentResult(@RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", resultService.getTournamentResult(tournament));
        return "results";
    }

    @GetMapping("contest/{id}")
    public String selectContest(@PathVariable("id") Long contestId, @RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", resultService.getTournamentResult(tournament));
        Contest contest = contestRepository.findOne(contestId);
        model.addAttribute("contest", contest);
        model.addAttribute("contestResult", resultService.getContestResult(contest));
        return "results";
    }

    @GetMapping("event/{id}")
    public String selectEvent(@PathVariable("id") Long eventId, @RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", resultService.getTournamentResult(tournament));
        Event event = eventRepository.findOne(eventId);
        Contest contest = event.getContest();
        model.addAttribute("contest", contest);
        model.addAttribute("contestResult", resultService.getContestResult(contest));
        model.addAttribute("event", event);
        model.addAttribute("eventResult", resultService.getEventResult(event));
        model.addAttribute("result", new Result());
        return "results";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestParam("tournament") Long tournamentId, @RequestParam("event") Long eventId,
                               @ModelAttribute Result result, Model model) {
        Event event = eventRepository.findOne(eventId);
        result.setEvent(event);
        resultRepository.save(result);
        return "redirect:/results/event/" + eventId + "?tournament=" + tournamentId;
    }

    @GetMapping("delete/{id}")
    public String deleteResult(@RequestParam("tournament") Long tournamentId, @RequestParam("event") Long eventId, @PathVariable Long id) {
        resultRepository.delete(id);
        return "redirect:/results/event/" + eventId + "?tournament="+tournamentId;
    }

    @GetMapping("edit/{id}")
    public String editResult(@RequestParam("tournament") Long tournamentId, @RequestParam("event") Long eventId, @PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", resultService.getTournamentResult(tournament));
        Event event = eventRepository.findOne(eventId);
        Contest contest = event.getContest();
        model.addAttribute("contest", contest);
        model.addAttribute("contestResult", resultService.getContestResult(contest));
        model.addAttribute("event", event);
        model.addAttribute("eventResult", resultService.getEventResult(event));
        Result result = resultRepository.findOne(id);
        model.addAttribute("result", result);
        return "results";
    }
}
