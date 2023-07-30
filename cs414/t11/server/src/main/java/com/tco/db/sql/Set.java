package com.tco.db.sql;

public class Set {
    private String sql;

    public Set(String sql) {
        this.sql = sql;
    }

    public Set set(String variable, String value) {
        this.sql += "\nSET ";
        this.sql += variable + "=" + value + ";";
        return this;
    }

    public Select select() {
        return new Select(this.toString());
    }

    @Override
    public String toString() {
        return this.sql;
    }
}
