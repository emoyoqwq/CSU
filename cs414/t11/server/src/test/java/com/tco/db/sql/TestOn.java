package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestOn {
    @Test
    public void testOn() {
        assertEquals(
            " ON users.user_username = matches.match_p1",
            new On("", "users.user_username", "matches.match_p1").toString()
        );
    }

    @Test
    public void testOnInnerJoin() {
        String expected =
            " ON users.user_username = matches.match_p1" +
            "\nINNER JOIN user_email";
        String actual =
            new On("", "users.user_username", "matches.match_p1")
            .innerJoin("user_email")
            .toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testWhere() {
        String expected = " ON users.user_username = matches.match_p1" +
            "\nWHERE email";
        String actual = new On("", "users.user_username", "matches.match_p1")
            .where("email").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testOnBuild() {
        String expected = " ON users.user_username = matches.match_p1;";
        String actual = new On("", "users.user_username", "matches.match_p1").build();
        assertEquals(expected, actual);
    }
}
