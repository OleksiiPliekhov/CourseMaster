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

@WebServlet(value = "/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String userId = req.getParameter("userId");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");

        User updatedUser = new User(Integer.parseInt(userId), firstname, lastname, password, 0);

        boolean res = false;
        try {
            res = DBUserUtils.updateUser(con, Integer.parseInt(userId), updatedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            resp.sendRedirect(req.getContextPath() + "/user?id=" + userId);
        } else {
            req.setAttribute("updatingError",  "Something went wrong, try again");
            req.getRequestDispatcher("/userUpdate").forward(req, resp);
        }
    }
}
