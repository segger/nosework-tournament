package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.domain.EventRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Event;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String showParticipants(@RequestParam("contest") Long contestId, Model model) {
        model.addAttribute("contest", contestId);
        model.addAttribute("events", eventRepository.findByContestId(contestId));
        model.addAttribute("event", new Event());
        return "events";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestParam("contest") Long contestId, @ModelAttribute Event event) {
        tournamentService.saveEvent(contestId, event);
        return "redirect:/events?contest="+contestId;
    }

    @GetMapping("delete/{id}")
    public String deleteParticipant(@RequestParam("contest") Long contestId, @PathVariable Long id) {
        eventRepository.delete(id);
        return "redirect:/events?contest="+contestId;
    }

    @GetMapping("edit/{id}")
    public String editParticipant(@PathVariable Long id, @RequestParam("contest") Long contestId, Model model) {
        model.addAttribute("contest", contestId);
        model.addAttribute("events", eventRepository.findByContestId(contestId));
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        return "events";
    }
}
