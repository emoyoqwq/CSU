package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBeforeWhere {
    private BeforeWhere bf;

    @BeforeEach
    public void beforeEach() {
        bf = new BeforeWhere();
    }

    @Test
    public void testWhere() {
        assertEquals(
            "\nWHERE bob",
            bf.where("bob").toString()
        );
    }

    @Test
    public void testWhereNot() {
        assertEquals(
            "\nWHERE NOT jill",
            bf.whereNot("jill").toString()
        );
    }
}
