package se.johannalynn.nosework.noseworktournament.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.model.Participant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    ParticipantRepository repository;

    public boolean loadTournament(MultipartFile file) {
        //TODO: check json, encoding, etc

        String content = null;
        try {
            content = new String(file.getBytes());
            List<Participant> participantList = mapper.readValue(content, new TypeReference<List<Participant>>(){});

            for(Participant participant : participantList) {
                repository.save(participant);
            }

            return true;
        } catch (IOException e) {
            //TODO: exception handling
        }
        return false;
    }

    public byte[] exportTournament() throws FileServiceException {
        List<Participant> participantList = new ArrayList<>();
        for(Participant participant : repository.findAll()) {
            participantList.add(participant);
        }

        try {
            byte[] buf = mapper.writeValueAsBytes(participantList);
            return buf;
        } catch (JsonProcessingException e) {
            throw new FileServiceException();
        }
    }
}
