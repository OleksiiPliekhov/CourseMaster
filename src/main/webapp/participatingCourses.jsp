<%@ page import="com.example.coursesSystem.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.coursesSystem.models.Course" %>
<%@ page import="com.example.coursesSystem.repositories.DBCourseUtils" %>
<%@ page import="java.sql.Connection" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int userId = Integer.parseInt(request.getParameter("userId"));
    List<Course> courses = DBCourseUtils.getUserCourses((Connection) session.getAttribute("connection"), userId);
    request.setAttribute("courses", courses);
%>

<html>
<head>
    <title>Participating courses</title>
</head>
<body>
<h1>Courses You Are Enrolled In</h1>
<%
        if (courses != null && !courses.isEmpty()) {
        for (Course c : courses) {
%>
<li>
    <a href="course?id=<%= c.getCourseId() %>"><%= c.getName() %> - <%= c.getDescription() %></a>
    <%
        if (c.getTeacherId() == userId) {
    %>
    <p>Your course</p>
    <%
    } else {
    %>
    <form action="course" method="post">
        <input type="hidden" name="courseId" value="<%= c.getCourseId() %>" />
        <input type="submit" value="Register">
    </form>
</li>
<%
    }
    }
    } else {
%>
<p>No courses available.</p>
<%
    }
%>
</ul>
</body>
</html>
