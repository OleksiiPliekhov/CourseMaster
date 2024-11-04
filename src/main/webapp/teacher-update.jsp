<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.coursesSystem.models.Teacher" %>
<%@ page import="com.example.coursesSystem.repositories.DBTeacherUtils" %>

<%
    int teacherId = Integer.parseInt(request.getParameter("teacherId"));
    Connection con = (Connection) session.getAttribute("connection");
    Teacher teacher = DBTeacherUtils.findTeacherById(con, teacherId);

%>

<html>
<head>
    <title>Update Teacher</title>
</head>
<body>
<h1>Update Course</h1>
<form action="teacherUpdate" method="post">
    <input type="hidden" name="teacherId" value="<%= teacher.getId() %>">
    <p>
        <label for="firstname">Name:</label>
        <input type="text" name="firstname" id="firstname" value="<%= teacher.getFirstname() %>">
    </p>
    <p>
        <label for="lastname">Name:</label>
        <input type="text" name="lastname" id="lastname" value="<%= teacher.getLastname() %>">
    </p>
    <p>
        <label for="qualification">Description:</label>
        <textarea name="qualification" id="qualification"><%= teacher.getQualification().name() %></textarea>
    </p>
    <p>
        <label for="experience">Max Students:</label>
        <input type="number" name="experience" id="experience" value="<%= teacher.getExperience() %>">
    </p>
    <p>
        <input type="submit" value="Update Teacher">
    </p>
</form>
</body>
</html>
