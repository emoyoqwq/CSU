package com.tco.db.sql;

public class Join extends BeforeWhere {
    static Join inner(String sql, String table) {
        return new Join(sql, "INNER JOIN", table);
    }

    private String sql;
    private String type;
    private String table;

    public Join(String sql, String type, String table) {
        this.sql = sql;
        this.type = type;
        this.table = table;
    }

    public On on(String lhs, String rhs) {
        return new On(this.toString(), lhs, rhs);
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        this.sql += "\n" + type + " " + table;
        return this.sql;
    }
}
