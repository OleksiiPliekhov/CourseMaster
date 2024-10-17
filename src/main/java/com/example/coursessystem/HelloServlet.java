package com.example.coursessystem;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import com.example.coursessystem.beans.User;
import com.example.coursessystem.service.DBUserUtils;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        User user;
        try {
            Connection connection = DBConnection.initDBConnection();

            user = DBUserUtils.findUser(connection, "Alex", "Pliekhov", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + user.getFirstname() + user.getLastname() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}