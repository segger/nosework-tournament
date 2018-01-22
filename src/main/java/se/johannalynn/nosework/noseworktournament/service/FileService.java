package se.johannalynn.nosework.noseworktournament.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    TournamentRepository tournamentRepository;

    public Tournament loadSingleTournament(MultipartFile file) {
        System.out.println("FileService.loadSingleTournament");
        String content = null;
        try {
            content = new String(file.getBytes());
            System.out.println("content: " + content);
            Tournament tournament = mapper.readValue(content, Tournament.class);
            tournamentRepository.save(tournament);

            return tournament;
        } catch (IOException e) {
            //TODO: exception handling
            System.out.println("msg=" + e.getMessage());
            System.out.println("cause=" + e.getCause());
        }
        return null;
    }

    public List<Tournament> loadTournaments(MultipartFile file) {
        //TODO: check json, encoding, etc

        String content = null;
        try {
            content = new String(file.getBytes());
            List<Tournament> tournaments = mapper.readValue(content, new TypeReference<List<Tournament>>(){});
            tournamentRepository.save(tournaments);

            return tournaments;
        } catch (IOException e) {
            //TODO: exception handling
        }
        return null;
    }

    public byte[] exportTournament(String name) throws FileServiceException {
        System.out.println("FileService.exportTournament [name=" + name + "]");
        Tournament tournament = tournamentRepository.findByName(name);

        if(tournament.getParticipants() != null) {
            System.out.println("nbrOfParticipants: " + tournament.getParticipants().size());
        }
        try {
            byte[] buf = mapper.writeValueAsBytes(tournament);
            return buf;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileServiceException();
        }
    }
}
