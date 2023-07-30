package com.tco.db.sql;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestExpressionClause {
    @Test
    public void testWhere() {
        assertEquals(
                "\nWHERE user.name",
                ExpressionClause.where("", "user.name").toString()
        );
    }

    @Test
    public void testWhereNot() {
        assertEquals(
                "\nWHERE NOT user.name",
                ExpressionClause.whereNot("", "user.name").toString()
        );
    }

    @Test
    public void testAnd() {
        assertEquals(
                "\nAND user.name",
                ExpressionClause.and("", "user.name").toString()
        );
    }

    @Test
    public void testAndNot() {
        assertEquals(
                "\nAND NOT user.name",
                ExpressionClause.andNot("", "user.name").toString()
        );
    }

    @Test
    public void testOr() {
        assertEquals(
                "\nOR user.name",
                ExpressionClause.or("", "user.name").toString()
        );
    }

    @Test
    public void testOrNot() {
        assertEquals(
                "\nOR NOT user.name",
                ExpressionClause.orNot("", "user.name").toString()
        );
    }

    @Test
    public void testEQ() {
        String expected = "\n thing1 = thing1";
        String actual = new ExpressionClause("","","thing1").eq("thing1").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testNE() {
        String expected = "\n thing1 <> thing2";
        String actual = new ExpressionClause("","","thing1").ne("thing2").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testLT() {
        String expected = "\n thing1 < thing2";
        String actual = new ExpressionClause("","","thing1").lt("thing2").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testLTE() {
        String expected = "\n thing1 <= thing2";
        String actual = new ExpressionClause("","","thing1").lte("thing2").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGT() {
        String expected = "\n thing1 > thing2";
        String actual = new ExpressionClause("","","thing1").gt("thing2").toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGTE() {
        String expected = "\n thing1 >= thing2";
        String actual = new ExpressionClause("","","thing1").gte("thing2").toString();
        assertEquals(expected, actual);
    }
}
