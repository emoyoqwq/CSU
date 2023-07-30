package com.tco.db.sql;

public class Limit {
    private String sql;
    private Integer n;

    public Limit(String sql, int n) {
        this.sql = sql;
        this.n = n;
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        this.sql += "\nLIMIT " + n.toString();
        return this.sql;
    }
}
