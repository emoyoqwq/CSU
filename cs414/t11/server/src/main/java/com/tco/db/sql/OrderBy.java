package com.tco.db.sql;

import java.util.ArrayList;

public class OrderBy {
    public class Direction {
        private String sql;
        private String dir;

        public Direction(String sql, String dir) {
            this.sql = sql;
            this.dir = dir;
        }

        public Limit limit(int n) {
            return new Limit(this.toString(), n);
        }

        public String build() {
            return this.toString() + ";";
        }

        @Override
        public String toString() {
            String result = " " + this.dir;
            return this.sql + result;
        }
    }

    private String sql;
    private ArrayList<String> columns;

    public OrderBy(String sql) {
        this.sql = sql;
        columns = new ArrayList<>();
    }

    public OrderBy column(String column) {
        this.columns.add(column);
        return this;
    }

    public OrderBy columns(String[] columns) {
        for (int i = 0; i < columns.length; ++i)
            this.columns.add(columns[i]);
        return this;
    }

    public ArrayList<String> getColumns() {
        return this.columns;
    }

    public Direction ascending() {
        return new Direction(this.toString(), "ASC");
    }

    public Direction descending() {
        return new Direction(this.toString(), "DESC");
    }

    public Limit limit(int n) {
        return new Limit(this.toString(), n);
    }

    @Override
    public String toString() {
        this.sql += "\nORDER BY";
        for (int i = 0; i < columns.size(); ++i) {
            if (i > 0)
                this.sql += ",";
            this.sql += " ";
            this.sql += columns.get(i);
        }
        return this.sql;
    }
}
