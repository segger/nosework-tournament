package se.johannalynn.nosework.noseworktournament.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.johannalynn.nosework.noseworktournament.domain.TournamentRepository;
import se.johannalynn.nosework.noseworktournament.entity.Tournament;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    TournamentRepository tournamentRepository;

    public Tournament loadTournament(MultipartFile file) {
        //TODO: check json, encoding, etc

        String content = null;
        try {
            content = new String(file.getBytes());
            Tournament tournament = mapper.readValue(content, Tournament.class);
            tournamentRepository.save(tournament);

            return tournament;
        } catch (IOException e) {
            //TODO: exception handling
        }
        return null;
    }

    public byte[] exportTournament(String name) throws FileServiceException {
        Tournament tournament = tournamentRepository.findByName(name);

        try {
            byte[] buf = mapper.writeValueAsBytes(tournament);
            return buf;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new FileServiceException();
        }
    }
}
