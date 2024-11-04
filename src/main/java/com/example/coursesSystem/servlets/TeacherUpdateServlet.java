package com.example.coursesSystem.servlets;

import com.example.coursesSystem.Degree;
import com.example.coursesSystem.models.Teacher;
import com.example.coursesSystem.models.User;
import com.example.coursesSystem.repositories.DBTeacherUtils;
import com.example.coursesSystem.repositories.DBUserUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/teacherUpdate")
public class TeacherUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String teacherId = req.getParameter("teacherId");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        String qualification = req.getParameter("qualification");
        String experience = req.getParameter("experience");

        Teacher updatedTeacher = new Teacher(Integer.parseInt(teacherId), firstname, lastname, password, Degree.valueOf(qualification), Integer.parseInt(experience));

        boolean res = false;
        try {
            res = DBTeacherUtils.updateTeacher(con, Integer.parseInt(teacherId), updatedTeacher);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(res) {
            resp.sendRedirect(req.getContextPath() + "/user?id=" + teacherId);
        } else {
            req.setAttribute("updatingError",  "Something went wrong, try again");
            req.getRequestDispatcher("/teacherUpdate").forward(req, resp);
        }
    }
}
