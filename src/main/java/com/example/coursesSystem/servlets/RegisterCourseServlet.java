package com.example.coursesSystem.servlets;

import com.example.coursesSystem.DBConnection;
import com.example.coursesSystem.models.Course;
import com.example.coursesSystem.models.Teacher;
import com.example.coursesSystem.models.User;
import com.example.coursesSystem.repositories.DBCourseUtils;
import com.example.coursesSystem.repositories.DBTeacherUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/course")
public class RegisterCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        try {
            con = DBConnection.initDBConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String courseIdParam = request.getParameter("id");
        if (courseIdParam != null) {
            int courseId = Integer.parseInt(courseIdParam);
            Course course = null;
            Teacher teacher = null;
            try {
                course = DBCourseUtils.findCourseById(con, courseId);
                assert course != null;
                teacher = DBTeacherUtils.findTeacherById(con, course.getTeacherId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(course != null) {
                request.setAttribute("course", course);
                request.setAttribute("teacher", teacher);
                request.getRequestDispatcher("/course-page.jsp").forward(request, response);
            }
        }

        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Course ID is missing.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int courseId = Integer.parseInt( (String) req.getAttribute("courseId"));
        User user = (User) session.getAttribute("user");
        Connection con = (Connection) session.getAttribute("connection");
        try {
            DBCourseUtils.registerOnCourse(con, courseId, user.getId());
            resp.sendRedirect("/courses");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
