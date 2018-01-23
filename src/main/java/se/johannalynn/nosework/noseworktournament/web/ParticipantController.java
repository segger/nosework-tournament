package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.entity.Participant;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String getParticipants(@RequestParam("tournament") Long tournamentId, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("participants", participantRepository.findAllByTournament(tournament));
        model.addAttribute("participant", new Participant());
        return "participants";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestParam("tournament") Long tournamentId, @ModelAttribute Participant participant) {
        tournamentService.saveParticipant(tournamentId, participant);
        return "redirect:/participants?tournament="+tournamentId;
    }

    @GetMapping("delete/{id}")
    public String deleteParticipant(@RequestParam("tournament") Long tournamentId, @PathVariable Long id) {
        participantRepository.delete(id);
        return "redirect:/participants?tournament="+tournamentId;
    }

    @GetMapping("edit/{id}")
    public String editParticipant(@RequestParam("tournament") Long tournamentId, @PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(tournamentId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("participants", participantRepository.findAllByTournament(tournament));
        Participant participant = participantRepository.findOne(id);
        model.addAttribute("participant", participant);
        return "participants";
    }
}
