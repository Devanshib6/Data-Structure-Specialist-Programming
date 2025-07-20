// ==== com.footwise.config.DBConnection.java ====
package com.footwise.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/footwise", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}