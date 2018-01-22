package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.johannalynn.nosework.noseworktournament.domain.ContestRepository;
import se.johannalynn.nosework.noseworktournament.domain.EventRepository;
import se.johannalynn.nosework.noseworktournament.domain.StashPointRepository;
import se.johannalynn.nosework.noseworktournament.entity.Contest;
import se.johannalynn.nosework.noseworktournament.entity.Event;
import se.johannalynn.nosework.noseworktournament.entity.StashPoint;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
import se.johannalynn.nosework.noseworktournament.service.TournamentService;

import java.util.ArrayList;
import java.util.HashSet;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StashPointRepository stashPointRepository;

    @Autowired
    TournamentService tournamentService;

    @GetMapping(value = {"","/*"})
    public String getEvents(@RequestParam("contest") Long contestId, Model model) {
        Tournament tournament = tournamentService.getTournamentByContestId(contestId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("contest", contestId);
        model.addAttribute("events", eventRepository.findByContestId(contestId));
        model.addAttribute("event", new Event());
        model.addAttribute("stash", new StashPoint());
        return "events";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(@RequestParam("contest") Long contestId, @ModelAttribute Event event) {
        tournamentService.saveEvent(contestId, event);
        return "redirect:/events?contest="+contestId;
    }

    @PostMapping("stashes/saveOrUpdate")
    public String saveOrUpdateStash(@RequestParam("contest") Long contestId, @RequestParam("event") Long eventId,
                                    @ModelAttribute StashPoint stashPoint) {
        Event event = eventRepository.findOne(eventId);
        if(event.getStashPoints() == null) {
            event.setStashPoints(new ArrayList<>());
        }
        event.getStashPoints().add(stashPoint);
        tournamentService.saveEvent(contestId, event);
        return "redirect:/events?contest="+contestId;
    }

    @GetMapping("delete/{id}")
    public String deleteEvent(@RequestParam("contest") Long contestId, @PathVariable Long id) {
        eventRepository.delete(id);
        return "redirect:/events?contest="+contestId;
    }

    @GetMapping("stashes/delete/{id}")
    public String deleteStash(@RequestParam("contest") Long contestId, @PathVariable Long id) {
        stashPointRepository.delete(id);
        return "redirect:/events?contest="+contestId;
    }

    @GetMapping("edit/{id}")
    public String editEvent(@PathVariable Long id, @RequestParam("contest") Long contestId, Model model) {
        Tournament tournament = tournamentService.getTournamentByContestId(contestId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("contest", contestId);
        model.addAttribute("events", eventRepository.findByContestId(contestId));
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        model.addAttribute("stash", new StashPoint());
        return "events";
    }

    @GetMapping("stashes/edit/{id}")
    public String editStash(@PathVariable Long id, @RequestParam("contest") Long contestId, Model model) {
        Tournament tournament = tournamentService.getTournamentByContestId(contestId);
        model.addAttribute("tournament", tournament);
        model.addAttribute("contest", contestId);
        model.addAttribute("events", eventRepository.findByContestId(contestId));
        StashPoint stashPoint = stashPointRepository.findOne(id);
        model.addAttribute("event", stashPoint.getEvent());
        model.addAttribute("stash", stashPoint);
        return "events";
    }
}
