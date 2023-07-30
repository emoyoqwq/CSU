package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestMatchPatch {

    private MatchPatch match;

    @BeforeEach
    public void createConfigurationForTestCases() {
        match = new MatchPatch();
        match.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"match\"")
    public void testType() {
        String type = match.getRequestType();
        assertEquals("match", type);
    }

    @Test
    @DisplayName("Static match ID is correct")
    public void testStaticMatchID() {
        String payload = match.getMatchID();
        assertEquals("0", payload);
    }

    @Test
    @DisplayName("Static player ID is correct")
    public void testStaticPlayerID() {
        String payload = match.getUserID();
        assertNull(payload);
    }

    @Test
    @DisplayName("Static Opponent ID is correct")
    public void testStaticOpponentID() {
        String payload = match.getOpponentID();
        assertNull(payload);
    }

    @Test
    @DisplayName("Static turn ID is correct")
    public void testStaticTurnID() {
        String payload = match.getTurnID();
        assertNull(payload);
    }

    @Test
    @DisplayName("Static Active status is correct")
    public void testStaticActiveStatus() {
        boolean payload = match.getActive();
        assertFalse(payload);
    }

    @Test
    @DisplayName("Static match data is correct")
    public void testStaticMatchData() {
        String payload = match.getMatchBoard();
        assertNull(payload);
    }
}