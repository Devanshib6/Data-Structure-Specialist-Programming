package com.footwise.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/shoes", "/userDashboard.jsp", "/adminDashboard.jsp"})
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("login.jsp");
        }
    }

    public void destroy() {}
}
