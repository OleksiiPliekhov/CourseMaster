package com.example.coursesSystem.servlets;

import com.example.coursesSystem.models.User;
import com.example.coursesSystem.utils.DBUserUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/deposite")
public class DepositeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Double amount = Double.parseDouble(req.getParameter("amount"));
        User user = (User) session.getAttribute("user");
        Connection con = (Connection) session.getAttribute("connection");
        try {
            DBUserUtils.depositMoney(con, user.getId(), amount);
            User updatedUser = DBUserUtils.findUserById(con, user.getId());
            req.setAttribute("userInfo", updatedUser);
            req.getRequestDispatcher("/user-page.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
