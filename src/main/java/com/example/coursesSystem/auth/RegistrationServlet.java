package com.example.coursesSystem.auth;

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

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Connection con = (Connection) session.getAttribute("connection");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        boolean res = false;
        try {
            if (role.equals("teacher")) {
                String qualification = req.getParameter("qualification");
                int experience = Integer.parseInt(req.getParameter("experience"));

                Teacher teacher = new Teacher();
                teacher.setFirstname(firstname);
                teacher.setLastname(lastname);
                teacher.setPassword(password);
                teacher.setQualification(Degree.valueOf(qualification));
                teacher.setExperience(experience);

                res = DBTeacherUtils.createTeacher(con, teacher);
            } else {
                User user = new User(0, firstname, lastname, password, 0);
                res = DBUserUtils.createUser(con, user);
            }

            if (res) {
                resp.sendRedirect("index.jsp");
            } else {
                req.setAttribute("error", "invalid");
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "invalid");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
