package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMatchPost {

    private MatchPost match;

    @BeforeEach
    public void createConfigurationForTestCases() {
        match = new MatchPost();
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
        String payload = match.getMatchID();
        assertEquals("0", payload);
    }
}