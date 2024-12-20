package com.example.coursesSystem.auth;


import com.example.coursesSystem.models.User;
import com.example.coursesSystem.utils.DBUserUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        if(firstname == null || lastname == null || password == null){
            response.sendRedirect(request.getContextPath() + "/login.jsp?error='Invalid first-/last-name or password'");
            return;
        } else {
            User user = null;
            try {
                user = DBUserUtils.findUser(con, firstname, lastname, password);
                if(user == null){
                    response.sendRedirect(request.getContextPath() + "/login.jsp?error=Invalid first-/last-name or password");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            session.setAttribute("user", user);
            response.sendRedirect("/index.jsp");
        }

    }
}

