package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;

@Controller
public class MainController {

    @Autowired
    ParticipantRepository participantRepository;

    @RequestMapping("/participants")
    public String showParticipants(Model model) {
        model.addAttribute("participants", participantRepository.findAll());
        return "participants";
    }
}
