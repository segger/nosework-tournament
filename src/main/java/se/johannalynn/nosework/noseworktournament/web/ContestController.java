package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

@Controller
@RequestMapping("/contests")
public class ContestController {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String getParticipants(@RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("contests", contestRepository.findAllByTournament(tournament));
        model.addAttribute("contest", new Contest());
        return "contests";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestParam("tournament") Long tournamentId, @ModelAttribute Contest contest) {
        tournamentService.saveContest(tournamentId, contest);
        return "redirect:/contests?tournament="+tournamentId;
    }

    @GetMapping("delete/{id}")
    public String deleteParticipant(@RequestParam("tournament") Long tournamentId, @PathVariable Long id) {
        contestRepository.delete(id);
        return "redirect:/contests?tournament="+tournamentId;
    }

    @GetMapping("edit/{id}")
    public String editParticipant(@RequestParam("tournament") Long tournamentId, @PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("contests", contestRepository.findAllByTournament(tournament));
        Contest contest = contestRepository.findOne(id);
        model.addAttribute("contest", contest);
        return "contests";
    }
}
