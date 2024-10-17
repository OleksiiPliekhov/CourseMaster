package com.example.coursessystem.servlets;

import com.example.coursessystem.DBConnection;
import com.example.coursessystem.beans.Course;
import com.example.coursessystem.beans.User;
import com.example.coursessystem.service.DBCourseUtils;
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
            con = DBConnection.initDBConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

        String name = (String) session.getAttribute("name");
        String description = (String) session.getAttribute("description");
        int maxStudentsAmount  = (int) session.getAttribute("maxStudentsAmount");
        int teacherId = (int) session.getAttribute("teacherId");

        Course newCourse = new Course(name, description, maxStudentsAmount, teacherId);
        boolean res = false;
        try {
             res = DBCourseUtils.createCourse(con, newCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            resp.sendRedirect(getServletContext() + "/courses.jsp");
        } else {
            session.setAttribute("creationError",  "Something went wrong, try again");
            resp.sendRedirect(getServletContext() + "/create-course-page.jsp");
        }

    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String name = (String) session.getAttribute("name");
        String description = (String) session.getAttribute("description");
        int maxStudentsAmount  = (int) session.getAttribute("maxStudentsAmount");
        int teacherId = (int) session.getAttribute("teacherId");

        Course updatedCourse = new Course(name, description, maxStudentsAmount, teacherId);
        boolean res = false;
        try {
            res = DBCourseUtils.updateCourse(con, updatedCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(res) {
            resp.sendRedirect(getServletContext() + "/course-page.jsp");
        } else {
            session.setAttribute("updateError",  "Something went wrong, try again");
            resp.sendRedirect(getServletContext() + "/update-course-page.jsp");
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
