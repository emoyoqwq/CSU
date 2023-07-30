package com.tco.db.sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuery {
    private Query q;

    @BeforeEach
    public void beforeEach() {
        q = new Query();
    }

    @Test
    public void testSelect() {
        String expected = "\nSELECT *";
        String actual = q.select().all().toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testSet() {
        assertEquals(
            "\nSET @phrase='%denver%';",
                q.set("@phrase", "'%denver%'").toString()
        );
    }
}
