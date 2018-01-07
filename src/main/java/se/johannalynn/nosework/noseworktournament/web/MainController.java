package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.model.Tournament;
import se.johannalynn.nosework.noseworktournament.service.FileService;

@Controller
public class MainController {

    @Autowired
    FileService fileService;

    @Autowired
    TournamentRepository repository;

    @GetMapping(value = {"", "/*"})
    public String home(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "index";
    }

    @PostMapping
    public String save(@ModelAttribute Tournament tournament, Model model) {
        repository.save(tournament);
        model.addAttribute("tournament", tournament);
        return "index";
    }

    @PostMapping("/import")
    public String importTournamentFile(@RequestAttribute("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        String message = null;
        Tournament tournament = fileService.loadTournament(file);
        if(tournament == null) {
            message = "Error during upload";
        } else {
            message = "Successfully uploaded";

            model.addAttribute("tournament", tournament);
        }
        redirectAttributes.addFlashAttribute("importMessage", message);
        return "index";
    }
}
