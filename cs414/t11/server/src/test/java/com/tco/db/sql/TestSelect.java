package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSelect {
    Select select;

    @BeforeEach
    public void beforeEach() {
        select = new Select("");
    }

    @Test
    public void testAll() {
        assertEquals(
            "\nSELECT *",
            select.all().toString()
        );
    }

    @Test
    public void testColumn() {
        assertEquals(
            "\nSELECT user.email, user.id",
            select.column("user.email").column("user.id").toString()
        );
    }

    @Test
    public void TestColumnAs() {
        assertEquals(
            "\nSELECT user.email AS email",
            select.columnAs("user.email", "email").toString()
        );
    }

    @Test
    public void testColumns() {
        assertEquals(
            "\nSELECT user.id, user.email, user.username, user.password",
            select.columns(new String[]{"user.id", "user.email","user.username","user.password"}).toString()
        );
    }

    @Test
    public void testSelectFrom() {
        String expected =
            "\nSELECT *" +
            "\nFROM users";
        String actual =
            select.all()
            .from("users")
            .toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectBuild() {
        String expected = "\nSELECT;";
        String actual = select.build();
        assertEquals(expected, actual);
    }
}
