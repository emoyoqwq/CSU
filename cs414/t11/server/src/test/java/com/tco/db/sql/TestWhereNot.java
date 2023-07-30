package com.tco.db.sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWhereNot {
    @Test
    public void testWhereNot() {
        assertEquals(
            "\nWHERE NOT jill",
            new WhereNot("", "jill").toString()
        );
    }
}
