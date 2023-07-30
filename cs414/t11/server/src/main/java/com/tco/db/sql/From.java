package com.tco.db.sql;

public class From extends BeforeWhere {
    private String sql;
    private String table;

    public From(String sql, String table) {
        this.sql = sql;
        this.table = table;
    }

    public Join innerJoin(String table) {
        return Join.inner(this.toString(), table);
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        this.sql += "\nFROM " + this.table;
        return this.sql;
    }
}
