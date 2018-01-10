package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.entity.Participant;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String showParticipants(Model model) {
        model.addAttribute("participants", participantRepository.findAll());
        model.addAttribute("participant", new Participant());
        return "participants";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute Participant participant) {
        tournamentService.saveParticipant(participant);
        return "redirect:/participants";
    }

    @GetMapping("delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantRepository.delete(id);
        return "redirect:/participants";
    }

    @GetMapping("edit/{id}")
    public String editParticipant(@PathVariable Long id, Model model) {
        model.addAttribute("participants", participantRepository.findAll());
        Participant participant = participantRepository.findOne(id);
        model.addAttribute("participant", participant);
        return "participants";
    }
}
