package com.tco.db.sql;

import java.sql.Statement;
import java.util.ArrayList;

public class Select {
    private String sql;
    private ArrayList<String> columns;

    public Select(String sql) {
        this.sql =  sql;
        columns = new ArrayList<>();
    }

    public Select all() {
        columns.add("*");
        return this;
    }

    public Select column(String column_id) {
        columns.add(column_id);
        return this;
    }

    public Select columnAs(String column_id, String as_name) {
        columns.add(column_id + " AS " + as_name);
        return this;
    }

    public Select columns(String[] columns) {
        for (int i = 0; i < columns.length; ++i)
            this.columns.add(columns[i]);
        return this;
    }

    public From from(String table) {
        return new From(this.toString(), table);
    }

    public String build() {
        return this.toString() + ";";
    }

    @Override
    public String toString() {
        String result = "\nSELECT";
        for (int i = 0; i < columns.size(); ++i) {
            result += " " + columns.get(i);
            if (i < columns.size()-1)
                result += ",";
        }
        return this.sql + result;
    }
}
