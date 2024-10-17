package com.example.coursessystem.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String loginURI = req.getContextPath() + "/login.jsp";

        String requestURI = req.getRequestURI();

        if (requestURI.contains("?")) {
            requestURI = requestURI.substring(0, requestURI.indexOf("?"));
        }


        boolean loggedIn = req.getSession(false) != null && req.getSession(false).getAttribute("user") != null;
        boolean loginRequest = requestURI.equals(loginURI) || requestURI.endsWith("/login") || requestURI.endsWith("/login.jsp");

        if (loggedIn || loginRequest) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}