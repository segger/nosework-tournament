package se.johannalynn.nosework.noseworktournament.service;

import org.junit.Test;
import se.johannalynn.nosework.noseworktournament.model.EventResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlacementCalculationTest {

    @Test
    public void testEmptyList() {
        List<EventResult> inputList = new ArrayList<>();
        PlacementCalculation placementCalculation = new PlacementCalculation(10,inputList);
        List<EventResult> resultList = placementCalculation.asEventResultList();
        assertEquals(0, resultList.size());
    }

    @Test
    public void testPlacementCalculation() {
        List<EventResult> inputList = new ArrayList<>();
        EventResult one = createEventResult(false, 25);
        EventResult two = createEventResult(false, 25);
        EventResult three = createEventResult(false, 0);
        EventResult four = createEventResult(true, 0);
        inputList.add(one);
        inputList.add(two);
        inputList.add(three);
        inputList.add(four);
        PlacementCalculation placementCalculation = new PlacementCalculation(10,inputList);
        List<EventResult> resultList = placementCalculation.asEventResultList();
        assertEquals(4, resultList.size());
        assertEquals("1", resultList.get(0).getPlacement());
        assertEquals("20", resultList.get(0).getDisplayTournamentPoints());
        assertEquals("3 + 5", resultList.get(3).getDisplayTournamentPoints());
    }

    private EventResult createEventResult(boolean sse, int points) {
        EventResult eventResult = new EventResult();
        eventResult.setSse(sse);
        eventResult.setPoints(points);
        return eventResult;
    }
}
