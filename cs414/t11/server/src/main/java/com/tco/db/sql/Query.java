package com.tco.db.sql;

public class Query {
    public Select select() {
        return new Select("");
    }

    public Set set(String variable, String value) {
        return new Set("").set(variable, value);
    }
}
