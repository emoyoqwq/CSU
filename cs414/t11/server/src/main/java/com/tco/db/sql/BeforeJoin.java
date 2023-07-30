package com.tco.db.sql;

public class BeforeJoin {
    public Join innerJoin(String table) {
        return Join.inner(this.toString(), table);
    }

    @Override
    public String toString() {
        return "";
    }
}
