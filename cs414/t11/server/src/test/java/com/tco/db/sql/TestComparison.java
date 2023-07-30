package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestComparison {

    @Test
    public void testEq() {
        assertEquals(
            " = 'john'",
            Comparison.eq("","'john'").toString()
        );
    }

    @Test
    public void testAnd() {
        assertEquals(
            "  one\nAND two",
            new Comparison("","","one").and("two").toString()
        );
    }

    @Test
    public void testAndNot() {
        assertEquals(
            "  one\nAND NOT two",
            new Comparison("","","one").andNot("two").toString()
        );
    }

    @Test
    public void testOrNot() {
        assertEquals(
            "  one\nOR NOT two",
            new Comparison("","","one").orNot("two").toString()
        );
    }
}
