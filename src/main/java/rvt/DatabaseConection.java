package rvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConection {

    private static final String DB_URL = "jdbc:sqlite:App.db";
    private Scanner scanner;

    public DatabaseConection(Scanner scanner) {
        this.scanner = scanner;
        new Product();
        new Category();
    }

    private Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        try (PreparedStatement pstmt = conn.prepareStatement("PRAGMA foreign_keys = ON;")) {
            pstmt.execute();
        }
        return conn;
    }
    public void addCategory() {
        String sql = "INSERT INTO Category(name, price, product_id) VALUES(?, ?, ?)";
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Ievadiet kategorijas nosaukumu: ");
            String name = scanner.nextLine();
            System.out.print("Ievadiet cenu: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Ievadiet produkta ID: ");
            int productId = Integer.parseInt(scanner.nextLine());
            
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, productId);
            pstmt.executeUpdate();
            System.out.println("Kategorija pievienota!");
        } catch (SQLException e) {
            throw new RuntimeException("Pievienošana neizdevās: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Kļūda: Nepareizs skaitlis!");
        }
    }

    public void addProduct() {
        String sql = "INSERT INTO Product(name) VALUES(?)";
        try(Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Ievadiet produkta nosaukumu: ");
            String name = scanner.nextLine();
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Produkts pievienots!");
        } catch (SQLException e) {
            throw new RuntimeException("Pievienošana neizdevās: " + e.getMessage());
        }
    }

    public void showCategory() {
        String sql = "SELECT * FROM Category";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query neizdevās: " + e.getMessage());
        }
    }

    public void showProduct() {
        String sql = "SELECT * FROM Product";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query neizdevās: " + e.getMessage());
        }
    }

    public void searchProductByCategory() {
        String sql = "SELECT p.name AS product_name, c.name AS category_name "
                   + "FROM Product p JOIN Category c ON p.id = c.product_id";
        try (
                Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("product_name") 
                                   + ", Category: " + rs.getString("category_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query neizdevās: " + e.getMessage());
        }
    }
}