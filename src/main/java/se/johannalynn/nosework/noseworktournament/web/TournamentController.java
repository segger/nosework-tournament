package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.model.Tournament;
import se.johannalynn.nosework.noseworktournament.service.FileService;
import se.johannalynn.nosework.noseworktournament.service.FileServiceException;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    FileService fileService;

    @Autowired
    TournamentRepository repository;

    @GetMapping(value = {"", "/*"})
    public String get(Model model) {
        for(Tournament tournament : repository.findAll()) {
            model.addAttribute("tournament", tournament);
        }
        return "tournament";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTournament(@PathVariable("id") String id, Model model) {
        Tournament tournament = repository.findOne(Long.valueOf(id));
        model.addAttribute("tournament", tournament);
        return "tournament";
    }

    @GetMapping(value = "/export/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> exportTournamentFile(@PathVariable("name") String name) throws FileServiceException {
        byte[] buf = fileService.exportTournament(name);

        //TODO: any-name?
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"" + name + ".json\"")
                .contentLength(buf.length)
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(new ByteArrayInputStream(buf)));
    }
}
