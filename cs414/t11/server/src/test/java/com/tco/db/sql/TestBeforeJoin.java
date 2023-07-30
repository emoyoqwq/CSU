package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBeforeJoin {
    @Test
    public void testBeforeJoinInnerJoin() {
        String expected = "\nINNER JOIN tableString";
        Join actualObject = new BeforeJoin().innerJoin("tableString");
        String actual = actualObject.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testBeforeJoinToString() {
        String expected = "";
        String actual = new BeforeJoin().toString();
        assertEquals(expected, actual);
    }
}
