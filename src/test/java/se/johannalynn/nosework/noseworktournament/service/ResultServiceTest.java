package se.johannalynn.nosework.noseworktournament.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import se.johannalynn.nosework.noseworktournament.domain.*;
import se.johannalynn.nosework.noseworktournament.model.Result;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResultServiceTest {

    @MockBean
    EventRepository eventMock;

    @MockBean
    ProtocolRepository protocolMock;

    @Autowired
    ResultService unitUnderTest;

    @Before
    public void init() {
        unitUnderTest.eventRepository = eventMock;
        unitUnderTest.protocolRepository = protocolMock;
    }

    @Test
    public void testEventResult() throws ParseException {
        final Long id = 1L;
        EventEntity mockedEvent = new EventEntity();
        given(eventMock.findOne(id)).willReturn(mockedEvent);
        List<ProtocolEntity> protocols = new ArrayList<>();
        ProtocolEntity first = createProtocolEntity(1L, mockedEvent, false, 25, "00:10", 0, "A");
        ProtocolEntity third = createProtocolEntity(10L, mockedEvent, false, 25, "00:10", 2,"C");
        ProtocolEntity forth = createProtocolEntity(23L, mockedEvent, true, 0, "01:30", 0,"D");
        ProtocolEntity second = createProtocolEntity(2L, mockedEvent, false, 25, "00:20", 0,"B");
        protocols.add(first);
        protocols.add(third);
        protocols.add(forth);
        protocols.add(second);
        given(protocolMock.findByEvent(mockedEvent)).willReturn(protocols);

        List<? extends Result> result = unitUnderTest.getResults(id, "EVENT");
        assertEquals(4, result.size());
        assertEquals("A & A", result.get(0).getParticipant());
    }

    @Test
    public void testEventResultPoints() throws ParseException {
        final Long id = 1L;
        EventEntity mockedEvent = new EventEntity();
        given(eventMock.findOne(id)).willReturn(mockedEvent);

        List<ProtocolEntity> protocols = new ArrayList<>();
        ProtocolEntity protocol1 = createProtocolEntity(10L, mockedEvent, false, 0, "00:10", 0,"C");
        ProtocolEntity protocol2 = createProtocolEntity(1L, mockedEvent, false, 25, "00:10", 0, "A");
        protocols.add(protocol1);
        protocols.add(protocol2);

        assertEquals("A", protocols.get(1).getParticipant().getOwner());

        given(protocolMock.findByEvent(mockedEvent)).willReturn(protocols);

        List<? extends Result> result = unitUnderTest.getResults(id, "EVENT");
        assertEquals(2, result.size());
        assertEquals("A & A", result.get(0).getParticipant());
    }

    @Test
    public void testEventResultErrorPoints() throws ParseException {
        final Long id = 1L;
        EventEntity mockedEvent = new EventEntity();
        given(eventMock.findOne(id)).willReturn(mockedEvent);

        List<ProtocolEntity> protocols = new ArrayList<>();
        ProtocolEntity protocol1 = createProtocolEntity(10L, mockedEvent, false, 25, "00:10", 1,"C");
        ProtocolEntity protocol2 = createProtocolEntity(1L, mockedEvent, false, 25, "00:10", 0, "A");
        protocols.add(protocol1);
        protocols.add(protocol2);

        assertEquals("A", protocols.get(1).getParticipant().getOwner());

        given(protocolMock.findByEvent(mockedEvent)).willReturn(protocols);

        List<? extends Result> result = unitUnderTest.getResults(id, "EVENT");
        assertEquals(2, result.size());
        assertEquals("A & A", result.get(0).getParticipant());
    }

    @Test
    public void testEventResultTime() throws ParseException {
        final Long id = 1L;
        EventEntity mockedEvent = new EventEntity();
        given(eventMock.findOne(id)).willReturn(mockedEvent);

        List<ProtocolEntity> protocols = new ArrayList<>();
        ProtocolEntity protocol1 = createProtocolEntity(10L, mockedEvent, false, 25, "00:20", 0,"C");
        ProtocolEntity protocol2 = createProtocolEntity(1L, mockedEvent, false, 25, "00:10", 0, "A");
        protocols.add(protocol1);
        protocols.add(protocol2);

        assertEquals("A", protocols.get(1).getParticipant().getOwner());

        given(protocolMock.findByEvent(mockedEvent)).willReturn(protocols);

        List<? extends Result> result = unitUnderTest.getResults(id, "EVENT");
        assertEquals(2, result.size());
        assertEquals("A & A", result.get(0).getParticipant());
    }

    private ProtocolEntity createProtocolEntity(long id, EventEntity event, boolean sse,
                                                int points, String timeStr, int errorPoints,
                                                String participantStr) throws ParseException {
        ProtocolEntity protocolEntity = new ProtocolEntity();
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Time time = new Time(sdf.parse(timeStr).getTime());
        protocolEntity.setTime(time);
        protocolEntity.setSse(sse);
        protocolEntity.setPoints(points);
        ParticipantEntity participant = new ParticipantEntity();
        participant.setOwner(participantStr);
        participant.setDog(participantStr);
        protocolEntity.setParticipant(participant);
        protocolEntity.setEvent(event);
        protocolEntity.setErrorPoints(errorPoints);
        protocolEntity.setId(id);
        return protocolEntity;
    }
}
