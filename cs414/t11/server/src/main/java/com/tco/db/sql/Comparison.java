package com.tco.db.sql;

//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

public class Comparison {
    static public Comparison eq(String sql, String rhs) {
        return new Comparison(sql, "=", rhs);
    }

    static public Comparison ne(String sql, String rhs) {
        return new Comparison(sql, "<>", rhs);
    }

    static public Comparison lt(String sql, String rhs) {
        return new Comparison(sql, "<", rhs);
    }

    static public Comparison lte(String sql, String rhs) {
        return new Comparison(sql, "<=", rhs);
    }

    static public Comparison gt(String sql, String rhs) {
        return new Comparison(sql, ">", rhs);
    }

    static public Comparison gte(String sql, String rhs) {
        return new Comparison(sql, ">=", rhs);
    }

    static public Comparison like(String sql, String rhs) {
        return new Comparison(sql, "LIKE", rhs);
    }

    private String sql;
    private String comparator;
    private String rhs;

    public Comparison(String sql, String comparator, String rhs) {
        this.sql = sql;
        this.comparator = comparator;
        this.rhs = rhs;
    }

    public ExpressionClause and(String lhs) {
        return ExpressionClause.and(this.toString(), lhs);
    }

    public ExpressionClause andNot(String lhs) {
        return ExpressionClause.andNot(this.toString(), lhs);
    }

    public ExpressionClause or(String lhs) {
        return ExpressionClause.or(this.toString(), lhs);
    }

    public ExpressionClause orNot(String lhs) {
        return ExpressionClause.orNot(this.toString(), lhs);
    }

    public OrderBy orderBy() {
        return new OrderBy(this.toString());
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        this.sql += " " + this.comparator + " " + this.rhs;
        return this.sql;
    }
}
