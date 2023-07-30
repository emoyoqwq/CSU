package com.tco.db.sql;

public class Where {
    protected String sql;
    protected String lhs;

    public Where(String sql, String lhs) {
        this.sql = sql;
        this.lhs = lhs;
    }

    @Override
    public String toString() {
        this.sql += "\nWHERE " + this.lhs;
        return this.sql;
    }
}
