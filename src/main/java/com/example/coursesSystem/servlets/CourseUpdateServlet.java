package com.example.coursesSystem.servlets;

import com.example.coursesSystem.models.Course;
import com.example.coursesSystem.repositories.DBCourseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(value = "/courseUpdate")
public class CourseUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String courseId = req.getParameter("courseId");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String maxStudentsAmount = req.getParameter("maxStudentsAmount");
        double price = Double.parseDouble(req.getParameter("price"));

        Course updatedCourse = new Course(name, description, Integer.parseInt(maxStudentsAmount), 0, price);

        boolean res = false;
        try {
            res = DBCourseUtils.updateCourse(con, Integer.parseInt(courseId), updatedCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            resp.sendRedirect(req.getContextPath() + "/course?id=" + courseId);
        } else {
            req.setAttribute("updatingError",  "Something went wrong, try again");
            req.getRequestDispatcher("/courseUpdate").forward(req, resp);
        }
    }
}
