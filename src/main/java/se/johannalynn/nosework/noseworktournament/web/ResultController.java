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

    @GetMapping(value = {"","/*"})
    public String getTournamentResult(@RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", mockTournamentResult());
        return "results";
    }

    private List<TournamentResult> mockTournamentResult() {
        List<TournamentResult> tournamentResult = new ArrayList<>();
        TournamentResult mockResult1 = new TournamentResult();
        Participant participant1 = new Participant();
        participant1.setOwner("Alpha");
        participant1.setDog("Doggie");
        mockResult1.setParticipant(participant1);
        mockResult1.setPlacement("1");
        mockResult1.setTotalPoints(175);
        tournamentResult.add(mockResult1);
        return tournamentResult;
    }

    private List<ContestResult> mockContestResult(Long id) {
        List<ContestResult> contestResults = new ArrayList<>();
        ContestResult mockResult1 = new ContestResult();
        Participant participant1 = new Participant();
        participant1.setOwner("Alpha");
        participant1.setDog("Doggie");
        mockResult1.setParticipant(participant1);
        mockResult1.setPlacement("1");
        mockResult1.setTotalPoints(100);
        contestResults.add(mockResult1);
        return contestResults;
    }

    @GetMapping("contest/{id}")
    public String selectContest(@PathVariable("id") Long contestId, @RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", mockTournamentResult());
        model.addAttribute("contest", contestRepository.findOne(contestId));
        model.addAttribute("contestResult", mockContestResult(contestId));
        return "results";
    }

    @GetMapping("event/{id}")
    public String selectEvent(@PathVariable("id") Long eventId, @RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("tournamentResult", mockTournamentResult());
        Event event = eventRepository.findOne(eventId);
        Contest contest = event.getContest();
        model.addAttribute("contest", contest);
        model.addAttribute("contestResult", mockContestResult(contest.getId()));
        model.addAttribute("event", event);
        return "results";
    }
}
