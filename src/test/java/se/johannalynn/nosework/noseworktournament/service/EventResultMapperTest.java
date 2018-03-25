package se.johannalynn.nosework.noseworktournament.service;

import org.junit.Test;
import se.johannalynn.nosework.noseworktournament.domain.ProtocolEntity;
import se.johannalynn.nosework.noseworktournament.model.EventResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EventResultMapperTest {

    @Test
    public void testEmptyList() {
        List<ProtocolEntity> inputList = new ArrayList<>();
        EventResultMapper eventResultMapper = new EventResultMapper(inputList, 10);
        List<EventResult> resultList = eventResultMapper.asEventResultList();
        assertEquals(0, resultList.size());
    }

    @Test
    public void testPlacementCalculation() {
        List<ProtocolEntity> inputList = new ArrayList<>();
        ProtocolEntity one = createProtocolEntity(false, 25);
        ProtocolEntity two = createProtocolEntity(false, 25);
        ProtocolEntity three = createProtocolEntity(false, 0);
        ProtocolEntity four = createProtocolEntity(true, 0);
        inputList.add(one);
        inputList.add(two);
        inputList.add(three);
        inputList.add(four);
        EventResultMapper eventResultMapper = new EventResultMapper(inputList, 10);
        List<EventResult> resultList = eventResultMapper.asEventResultList();
        assertEquals(4, resultList.size());
        assertEquals("1", resultList.get(0).getPlacement());
        assertEquals("20", resultList.get(0).getTournamentPointsPresentation());
        assertEquals("3 + 5", resultList.get(3).getTournamentPointsPresentation());
    }

    private ProtocolEntity createProtocolEntity(boolean sse, int points) {
        ProtocolEntity protocolEntity = new ProtocolEntity();
        protocolEntity.setSse(sse);
        protocolEntity.setPoints(points);
        return protocolEntity;
    }
}
