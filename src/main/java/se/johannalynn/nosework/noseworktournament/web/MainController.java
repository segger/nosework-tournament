package se.johannalynn.nosework.noseworktournament.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.johannalynn.nosework.noseworktournament.model.Participant;
import se.johannalynn.nosework.noseworktournament.service.FileService;
import se.johannalynn.nosework.noseworktournament.service.FileServiceException;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @PostMapping("/import")
    public String importTournamentFile(@RequestAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = null;
        if(fileService.loadTournament(file)) {
            message = "Successfully uploaded";
        } else {
            message = "Error during upload";
        }
        redirectAttributes.addFlashAttribute("importMessage", message);
        return "redirect:/";
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> exportTournamentFile() throws FileServiceException {
        byte[] buf = fileService.exportTournament();

        //TODO: any-name?
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"tournament.json\"")
                .contentLength(buf.length)
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(new ByteArrayInputStream(buf)));
    }
}
