package com.example.coursesSystem.servlets;

import com.example.coursesSystem.models.Course;
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

@WebServlet(name="courseServlet", value = "/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        try {
            session.setAttribute("courses", DBCourseUtils.findAllCourses(con));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect( "/courses.jsp");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String name =  req.getParameter("name");
        String description =  req.getParameter("description");
        int maxStudentsAmount  = Integer.parseInt(req.getParameter("maxStudentsAmount"));
        int teacherId = Integer.parseInt(req.getParameter("teacherId"));
        double price = Double.parseDouble(req.getParameter("price"));

        Course newCourse = new Course(name, description, maxStudentsAmount, teacherId, price);
        boolean res = false;
        try {
             res = DBCourseUtils.createCourse(con, newCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            resp.sendRedirect( "courses");
        } else {
            resp.sendRedirect("/createCourse.jsp");
        }

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        int courseId = (int) req.getAttribute("courseId");
        User user = (User) session.getAttribute("user");
        try {
            DBCourseUtils.deleteUser(con, courseId, user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
