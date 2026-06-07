package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Category {

    private static final String DB_URL = "jdbc:sqlite:App.db";

    public Category() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS Category ("
                + "id INTEGER PRIMARY KEY NOT NULL, "
                + "name TEXT NOT NULL"
                + ");";
        try (
            Connection conn = connect();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute("PRAGMA foreign_keys = ON;");
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: "
                    + e.getMessage());
        }
    }
}
