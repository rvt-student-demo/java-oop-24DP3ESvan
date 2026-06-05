error id: file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/DatabaseConection.java:
file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/DatabaseConection.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 829
uri: file:///C:/Users/A240393ES/Documents/java-oop-24DP3ESvan/src/main/java/rvt/DatabaseConection.java
text:
```scala
package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConection {

    private static final String DB_URL = "jdbc:sqlite:app.db";

    public DatabaseConection() {
        // No schema initialization here, as it's handled by Product and Category
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (PreparedStatement pstmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            pstmt.execute();
        }
        return conn;
    }
    public void addCategory() {

    }

    public void addProduct() {

    }

    public void showProduct() {

    }


    public@@ void showCategory() {
        String sql = "SELECT * FROM Category";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
    }

    public void searchProductByCategory() {

    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 