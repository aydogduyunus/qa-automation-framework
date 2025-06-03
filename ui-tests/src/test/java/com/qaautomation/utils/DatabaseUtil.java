package com.qaautomation.utils;

import java.sql.*;

public class DatabaseUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/qa_form_data";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Yunus719719"; // kendi şifreni yaz

    public static void insertFormData(String name, String email, String currentAddress, String permanentAddress, String browser) {
        String insertSQL = "INSERT INTO form_submissions (name, email, current_address, permanent_address, browser) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, currentAddress);
            stmt.setString(4, permanentAddress);
            stmt.setString(5, browser);

            stmt.executeUpdate();
            System.out.println("✅ Form verisi veritabanına kaydedildi.");
        } catch (SQLException e) {
            System.err.println("❌ Veritabanı hatası: " + e.getMessage());
        }
    }

    public static boolean isFormDataPresent(String name, String email) {
        String query = "SELECT COUNT(*) FROM form_submissions WHERE name = ? AND email = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ SELECT sorgusu hatası: " + e.getMessage());
        }

        return false;
    }
}