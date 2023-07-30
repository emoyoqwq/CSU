package com.tco.db.sql;

public class WhereNot extends Where {
    public WhereNot(String sql, String lhs) {
        super(sql, lhs);
    }

    @Override
    public String toString() {
        this.sql += "\nWHERE NOT " + this.lhs;
        return this.sql;
    }
}
