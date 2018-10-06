package se.johannalynn.nosework.noseworktournament.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johannalynn.nosework.noseworktournament.domain.ProtocolEntity;
import se.johannalynn.nosework.noseworktournament.domain.ProtocolRepository;

import java.util.List;

@RestController
@RequestMapping("/api/protocols")
public class ProtocolController {

    @Autowired
    ProtocolRepository protocolRepository;

    @PostMapping("/list")
    public Iterable<ProtocolEntity> saveProtocolList(@RequestBody List<ProtocolEntity> protocolList) {
        return protocolRepository.save(protocolList);
    }
}
