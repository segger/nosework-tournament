package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.domain.EventRepository;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Event;
import se.johannalynn.nosework.noseworktournament.entity.Participant;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
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
        return "results";
    }
}
