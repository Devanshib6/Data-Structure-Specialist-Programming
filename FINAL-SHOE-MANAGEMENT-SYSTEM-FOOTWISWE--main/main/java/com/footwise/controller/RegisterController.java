package com.footwise.controller;

import com.footwise.config.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            int i = ps.executeUpdate();
            if (i > 0) {
                request.setAttribute("successMessage", "Registration successful! Please login.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Username already exists or invalid data.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
