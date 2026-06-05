package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConection {

    private static final String DB_URL = "jdbc:sqlite:App.db";
    private Scanner scanner;

    public DatabaseConection(Scanner scanner) {
        this.scanner = scanner;
        new Category();
        new Product();
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (PreparedStatement pstmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            pstmt.execute();
        }
        return conn;
    }
    public void addCategory() {
        String sql = "INSERT INTO Category(name) VALUES(?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Ievadiet kategorijas nosaukumu: ");
            String name = scanner.nextLine();
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Kategorija pievienota!");
        } catch (SQLException e) {
            System.out.println("Kluda: " + e.getMessage());
        }
    }

    public void addProduct() {
        String sql = "INSERT INTO Product(name, price, category_id) VALUES(?, ?, ?)";
        try (Connection conn = connect()) {
            System.out.print("Ievadiet produkta nosaukumu: ");
            String name = scanner.nextLine();
            System.out.print("Ievadiet cenu: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Ievadiet kategorijas ID: ");
            int categoryId = Integer.parseInt(scanner.nextLine());

            String checkSql = "SELECT id FROM Category WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, categoryId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("Kluda: Kategorija ar ID " + categoryId + " neeksiste!");
                        return;
                    }
                }
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setDouble(2, price);
                pstmt.setInt(3, categoryId);
                pstmt.executeUpdate();
                System.out.println("Produkts pievienots!");
            }
        } catch (SQLException e) {
            System.out.println("Kluda: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Kluda: Nepareizs skaitlis!");
        }
    }

    private void showTable(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            while(rs.next()) {
                StringBuilder row = new StringBuilder();
                for(int i = 1; i <= cols; i++) {
                    if(i > 1) row.append(", ");
                    row.append(meta.getColumnLabel(i)).append(": ");
                    row.append(rs.getString(i));
                }
                System.out.println(row);
            }
        } catch(SQLException e) {
            throw new RuntimeException("Query neizdevas: " + e.getMessage());
        }
    }

    public void showCategory() {
        showTable("Category");
    }

    public void showProduct() {
        showTable("Product");
    }

    private void searchAndDisplay(String sql, int categoryId, String categoryName) throws SQLException {
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if(categoryId > 0) pstmt.setInt(1, categoryId);
            else pstmt.setString(1, "%" + categoryName + "%");
            
            try(ResultSet rs = pstmt.executeQuery()) {
                if(!rs.next()) {
                    System.out.println("Nekas netika atrasts.");
                } else {
                    System.out.println("\nMeklesanas rezultati: ");
                    do {
                        System.out.println("Produkts: " + rs.getString("product_name") 
                                         + ", Kategorija: " + rs.getString("category_name")
                                         + ", Cena: " + rs.getDouble("product_price"));
                    } while(rs.next());
                }
            }
        }
    }

    public void searchProductByCategory() {
        System.out.println("\nMeklesana pec kategorijas:");
        System.out.println("1. Meklet pec kategorijas ID");
        System.out.println("2. Meklet pec kategorijas nosaukuma");
        System.out.print("Izvēlieties: ");
        String choice = scanner.nextLine();
        
        String sql = "SELECT p.id AS product_id, p.name AS product_name, p.price AS product_price, c.id AS category_id, c.name AS category_name "
                   + "FROM Product p JOIN Category c ON p.category_id = c.id WHERE c.id = ?";
        
        try {
            if("1".equals(choice)) {
                System.out.print("Ievadiet kategorijas ID: ");
                searchAndDisplay(sql, Integer.parseInt(scanner.nextLine()), "");
            } else if("2".equals(choice)) {
                System.out.print("Ievadiet kategorijas nosaukumu: ");
                sql = sql.replace("WHERE c.id = ?", "WHERE c.name LIKE ?");
                searchAndDisplay(sql, -1, scanner.nextLine());
            } else {
                System.out.println("Nepareiza izvele!");
            }
        } catch(NumberFormatException e) {
            System.out.println("Kluda: Nepareizs skaitlis!");
        } catch(SQLException e) {
            throw new RuntimeException("Query neizdevas: " + e.getMessage());
        }
    }
}