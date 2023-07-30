package com.tco.db.sql;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestJoin {
    @Test
    public void testInnerJoin() {
        assertEquals(
            "\nINNER JOIN email",
            Join.inner("", "email").toString()
        );
    }

    @Test
    public void testInnerJoinOn() {
        assertEquals(
            "\nINNER JOIN email ON users.user_username = matches.match_p1",
            Join.inner("", "email")
                .on("users.user_username", "matches.match_p1")
                .toString()
        );
    }

    @Test
    public void testJoinBuild() {
        String expected = "\nINNER JOIN email;";
        String actual = Join.inner("", "email").build();
        assertEquals(expected, actual);
    }
}
