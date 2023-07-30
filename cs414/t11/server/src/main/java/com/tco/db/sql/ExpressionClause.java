package com.tco.db.sql;

import java.beans.Expression;

public class ExpressionClause {
    public static ExpressionClause where(String sql, String lhs) {
        return new ExpressionClause(sql, "WHERE", lhs);
    }

    public static ExpressionClause whereNot(String sql, String lhs) {
        return new ExpressionClause(sql, "WHERE NOT", lhs);
    }

    public static ExpressionClause and(String sql, String lhs) {
        return new ExpressionClause(sql, "AND", lhs);
    }

    public static ExpressionClause andNot(String sql, String lhs) {
        return new ExpressionClause(sql, "AND NOT", lhs);
    }

    public static ExpressionClause or(String sql, String lhs) {
        return new ExpressionClause(sql, "OR", lhs);
    }

    public static ExpressionClause orNot(String sql, String lhs) {
        return new ExpressionClause(sql, "OR NOT", lhs);
    }

    private String sql;
    private String type;
    private String lhs;

    public ExpressionClause(String sql, String type, String lhs) {
        this.sql = sql;
        this.type = type;
        this.lhs = lhs;
    }

    public Comparison eq(String rhs) {
        return Comparison.eq(this.toString(), rhs);
    }

    public Comparison ne(String rhs) {
        return Comparison.ne(this.toString(), rhs);
    }

    public Comparison lt(String rhs) {
        return Comparison.lt(this.toString(), rhs);
    }

    public Comparison lte(String rhs) {
        return Comparison.lte(this.toString(), rhs);
    }

    public Comparison gt(String rhs) {
        return Comparison.gt(this.toString(), rhs);
    }

    public Comparison gte(String rhs) {
        return Comparison.gte(this.toString(), rhs);
    }

    public Comparison like(String rhs) {
        return Comparison.like(this.toString(), rhs);
    }


    @Override
    public String toString() {
        this.sql += "\n" + this.type + " " + lhs;
        return this.sql;
    }
}
