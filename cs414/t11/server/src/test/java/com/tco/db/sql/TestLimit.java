package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestLimit {
    @Test
    public void testLimit() {
        assertEquals(
            "\nLIMIT 10",
                new Limit("", 10).toString()
        );
    }
}
