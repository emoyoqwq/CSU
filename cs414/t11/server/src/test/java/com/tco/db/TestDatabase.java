package com.tco.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

public class TestDatabase {
    private Database db;
    private boolean isUsingTunnel;

    @BeforeEach
    public void beforeEach() {
        db = new Database();

        isUsingTunnel = ("true").equals(System.getenv("CS414_USE_DATABASE_TUNNEL"));
    }
}
