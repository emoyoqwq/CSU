package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestWhere {
    @Test
    public void testWhere() {
        assertEquals(
            "\nWHERE jack",
            new Where("", "jack").toString()
        );
    }
}
