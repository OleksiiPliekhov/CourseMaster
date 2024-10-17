package com.example.coursessystem.filter;

import com.example.coursessystem.DBConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();

        Connection connection = (Connection) session.getAttribute("connection");

        if (connection == null) {
            try {
                // Создаем новое подключение к базе данных
                connection = DBConnection.initDBConnection();
                session.setAttribute("connection", connection);
            } catch (SQLException e) {
                throw new ServletException("Cannot establish a database connection", e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
