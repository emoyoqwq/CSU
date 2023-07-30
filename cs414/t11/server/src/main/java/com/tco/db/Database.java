package com.tco.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Database {
    private final static String URL_TUNNEL = "jdbc:mariadb://127.0.0.1:36101/cs414";
    private final static String URL_SERVER = "jdbc:mariadb://faure.cs.colostate.edu/cs414";
    private final static String USER = "haltab";
    private final static String PASSWORD = "832840072";

    private final transient Logger log = LoggerFactory.getLogger(Database.class);
    private Connection connection;

    public ResultSet query(String sql) {
        try {
            Connection conn = DriverManager.getConnection(getUrl(), USER, PASSWORD);
            log.info(getUrl());
            return conn.createStatement().executeQuery(sql);
        }
        catch (Exception e) {
            log.error("Exception: " + e.getMessage());
        }
        return null;
    }

    private String getUrl() {
        if (("true").equals(System.getenv("CS414_USE_DATABASE_TUNNEL")))
            return URL_TUNNEL;
        return URL_SERVER;
    }
}