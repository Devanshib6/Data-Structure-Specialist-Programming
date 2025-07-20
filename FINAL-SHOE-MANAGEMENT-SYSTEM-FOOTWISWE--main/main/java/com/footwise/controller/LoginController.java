package com.footwise.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.footwise.config.DBConnection;

// Use a single Jakarta EE module
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                // Session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Cookie
                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60 * 24); // 1 day
                response.addCookie(userCookie);

                if ("admin".equals(role)) {
                    response.sendRedirect("shoes");
                } else {
                    response.sendRedirect("userDashboard.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}