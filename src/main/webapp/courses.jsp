<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursesSystem.models.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.coursesSystem.models.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.coursesSystem.repositories.DBCourseUtils" %>

<html>
<head>
    <title>Courses Page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>All Courses</h1>
<ul>
    <%
        int userId = ((User) session.getAttribute("user")).getId();
        ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");

        if (courses != null && !courses.isEmpty()) {
            for (Course c : courses) {
    %>
    <li>
        <a href="course?courseId=<%= c.getCourseId() %>"><%= c.getName() %> - <%= c.getDescription() %></a>
        <p><strong>Price:</strong> <%= c.getPrice() %></p>
        <%
            if (c.getTeacherId() == userId) {
        %>
        <p>Your course</p>
        <%
            // if user already registered for the course, display a message, user DBCourseUtils.isUserRegisteredOnCourse() method
            } else if (DBCourseUtils.isUserRegisteredOnCourse((Connection) session.getAttribute("connection"), c.getCourseId(), userId)) {
        %>
        <p>You are already registered for this course.</p>
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
<a href="createCourse.jsp">Create New Course</a>
</body>
</html>
