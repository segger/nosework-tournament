package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.johannalynn.nosework.noseworktournament.domain.TournamentEntity;
import se.johannalynn.nosework.noseworktournament.service.FileService;

import java.io.ByteArrayInputStream;

@RestController
public class IOController {

    @Autowired
    FileService fileService;

    @PostMapping("/import")
    public ResponseEntity importTournamentFile(@RequestAttribute("file") MultipartFile file) {
        TournamentEntity tournament = fileService.loadSingleTournament(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/export/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> exportTournamentFile(@PathVariable("name") String name) {
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
