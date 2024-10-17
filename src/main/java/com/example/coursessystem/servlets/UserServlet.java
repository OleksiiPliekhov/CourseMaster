package com.example.coursessystem.servlets;

import com.example.coursessystem.beans.User;
import com.example.coursessystem.service.DBTeacherUtils;
import com.example.coursessystem.service.DBUserUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        try {
            if(DBTeacherUtils.isTeacher(con, userId)){
                req.setAttribute("teacherId", userId);
                req.getRequestDispatcher("/teacher").forward(req, resp);
            } else {
                User res = DBUserUtils.findUserById(con, userId);
                req.setAttribute("user", res);
                //TODO: not sure in this redirect and think about user data update
                assert res != null;
                user.updateFromDb(res);
                req.getRequestDispatcher("/user-page.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        String firstname = (String) req.getAttribute("firstname");
        String lastname = (String) req.getAttribute("lastname");
        String password = (String) req.getAttribute("password");

        User updatedUser = new User(0, firstname, lastname, password, 0);
        user.updateFromDb(updatedUser);
        try {
            if(DBUserUtils.updateUser(con, user.getId(), updatedUser)){
                req.setAttribute("user", user);
                req.getRequestDispatcher("/user-page.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "updateError");
                req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
