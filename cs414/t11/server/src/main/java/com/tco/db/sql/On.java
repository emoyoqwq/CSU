package com.tco.db.sql;

public class On extends BeforeJoin {
    private String sql;
    private String lhs;
    private String rhs;

    public On(String sql, String lhs, String rhs) {
        this.sql = sql;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Join innerJoin(String table) {
        return Join.inner(this.toString(), table);
    }

    public ExpressionClause where(String column) {
        return ExpressionClause.where(this.toString(), column);
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        this.sql += " ON " + lhs + " = " + rhs;
        return this.sql;
    }
}
