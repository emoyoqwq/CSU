package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUserPost {

    private UserPost user;

    @BeforeEach
    public void createConfigurationForTestCases() {
        user = new UserPost();
        user.buildResponse();
    }

    @Test
    @DisplayName("Request type is \"user\"")
    public void testType() {
        String type = user.getRequestType();
        assertEquals("user", type);
    }
}