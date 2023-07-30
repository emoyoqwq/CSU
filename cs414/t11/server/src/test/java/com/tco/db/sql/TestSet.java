package com.tco.db.sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSet {
    private Set set;

    @BeforeEach
    public void beforeEach() {
        set = new Set("");
    }

    @Test
    public void testSet() {
        String expected =
            "\nSET @phrase='%thing1%';" +
            "\nSET @phrase='%thing2%';" +
            "\nSET @phrase='%thing3%';";
        String actual = set
            .set("@phrase", "'%thing1%'")
            .set("@phrase", "'%thing2%'")
            .set("@phrase", "'%thing3%'")
            .toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetThenSelect() {
        String expected =
            "\nSET @phrase='%thing1%';" +
            "\nSELECT *";
        String actual = set
            .set("@phrase", "'%thing1%'")
            .select().all()
            .toString();
        assertEquals(expected, actual);
    }
}
