package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.tco.db.sql.OrderBy;
import java.util.ArrayList;

public class TestOrderBy {
    @Test
    public void testOrderByDirectionBuild() {
        String expected = " ASC;";
        OrderBy actualObject = new OrderBy("");
        String actual = actualObject.new Direction("", "ASC").build();
        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByColumns() {
        OrderBy expectedObject = new OrderBy("");
        expectedObject.column("thing1");
        expectedObject.column("thing2");
        expectedObject.column("thing3");
        ArrayList<String> expected = expectedObject.getColumns();
        OrderBy actualObject = new OrderBy("");
        String[] columnsArray = {"thing1", "thing2", "thing3"};
        actualObject.columns(columnsArray);
        ArrayList<String> actual = actualObject.getColumns();
        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByDescending() {
        String expected = "\nORDER BY DESC;";
        OrderBy.Direction actualObject = new OrderBy("").descending();
        String actual = actualObject.build();
        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByLimit() {
        String expected = "\nORDER BY\nLIMIT 10";
        OrderBy actualObject = new OrderBy("");
        String actual = actualObject.limit(10).toString();
        assertEquals(expected, actual);
    }
}
