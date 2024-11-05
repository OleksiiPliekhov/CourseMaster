package com.example.coursesSystem.servlets;

import com.example.coursesSystem.models.User;
import com.example.coursesSystem.utils.DBCourseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/leaveCourse")
public class CourseLeavingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int courseId = Integer.parseInt((String) req.getParameter("courseId"));
        User user = (User) session.getAttribute("user");
        Connection con = (Connection) session.getAttribute("connection");
        try {
            DBCourseUtils.leaveCourse(con, courseId, user.getId());
            req.getRequestDispatcher("/participatedCourses.jsp?userId=" + user.getId()).forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
