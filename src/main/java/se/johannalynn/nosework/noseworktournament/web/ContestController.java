package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

@Controller
@RequestMapping("/contests")
public class ContestController {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String showParticipants(Model model) {
        model.addAttribute("contests", contestRepository.findAll());
        model.addAttribute("contest", new Contest());
        return "contests";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute Contest contest) {
        tournamentService.saveContest(contest);
        return "redirect:/contests";
    }

    @GetMapping("delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        contestRepository.delete(id);
        return "redirect:/contests";
    }

    @GetMapping("edit/{id}")
    public String editParticipant(@PathVariable Long id, Model model) {
        model.addAttribute("contests", contestRepository.findAll());
        Contest contest = contestRepository.findOne(id);
        model.addAttribute("contest", contest);
        return "contests";
    }
}
