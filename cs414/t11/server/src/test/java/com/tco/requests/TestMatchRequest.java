package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMatchRequest {

    private MatchRequest match;

    @BeforeEach
    public void createConfigurationForTestCases() {
        match = new MatchRequest();
        match.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"match\"")
    public void testType() {
        String type = match.getRequestType();
        assertEquals("match", type);
    }

    @Test
    @DisplayName("Static response is correct")
    public void testStaticResponse() {
        String payload = match.getMatch();
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", payload);
    }
}