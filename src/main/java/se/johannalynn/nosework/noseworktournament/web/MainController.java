package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;
import se.johannalynn.nosework.noseworktournament.service.FileService;

@Controller
public class MainController {

    @Autowired
    FileService fileService;

    @Autowired
    TournamentRepository tournamentRepository;

    private void setAttributes(Model model) {
        model.addAttribute("tournaments", tournamentRepository.findAll());
        model.addAttribute("tournament", new Tournament());
    }

    @GetMapping(value = {"", "/*"})
    public String home(Model model) {
        setAttributes(model);
        return "index";
    }

    @PostMapping
    public String save(@ModelAttribute Tournament tournament, Model model) {
        tournamentRepository.save(tournament);
        setAttributes(model);
        return "index";
    }

    @PostMapping("/import")
    public String importTournamentFile(@RequestAttribute("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        String message = null;
        Tournament tournament = fileService.loadSingleTournament(file);
        if(tournament == null) {
            message = "Error during upload";
            setAttributes(model);
        } else {
            message = "Successfully uploaded";
            model.addAttribute("tournaments", tournamentRepository.findAll());
            model.addAttribute("tournament", tournament);
        }
        model.addAttribute("importMessage", message);
        return "index";
    }
}
