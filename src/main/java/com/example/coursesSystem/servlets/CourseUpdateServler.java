package com.example.coursesSystem.servlets;

import com.example.coursesSystem.beans.Course;
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
public class CourseUpdateServler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");
        int courseId = (int) session.getAttribute("courseId");

        String name = (String) session.getAttribute("name");
        String description = (String) session.getAttribute("description");
        int maxStudentsAmount  = (int) session.getAttribute("maxStudentsAmount");

        Course updatedCourse = new Course(name, description, maxStudentsAmount, 0);

        boolean res = false;
        try {
            res = DBCourseUtils.updateCourse(con, courseId, updatedCourse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            req.setAttribute("course", updatedCourse); // Устанавливаем атрибут для передачи данных курса
            req.getRequestDispatcher("/course-page.jsp").forward(req, resp); // Используем forward для перехода с сохранением атрибутов
        } else {
            session.setAttribute("updatingError",  "Something went wrong, try again");
            resp.sendRedirect(getServletContext() + "/course-update.jsp");
        }
    }
}
