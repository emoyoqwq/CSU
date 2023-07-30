package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFrom {
    @Test
    public void testTable() {
        assertEquals(
            "\nFROM users",
            new From("", "users").toString()
        );
    }

    @Test
    public void testInnerJoin() {
        String expected =
            "\nFROM user_email" +
            "\nINNER JOIN match_p1";
        String actual =
            new From("", "user_email")
                .innerJoin("match_p1")
                .toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testFrom() {
        String expected =
            "\nFROM users" +
            "\nWHERE user_id";
        String actual =
            new From("", "users")
                .where("user_id")
                .toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testFromBuild() {
        String expected = "\nFROM users;";
        String actual = new From("", "users").build();
        assertEquals(expected, actual);
    }
}
