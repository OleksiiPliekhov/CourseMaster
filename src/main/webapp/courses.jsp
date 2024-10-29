<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursesSystem.beans.Course" %>
<%@ page import="java.util.ArrayList" %>


<html>
<head>
    <title>Courses Page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>All Courses</h1>
<ul>
    <%
        ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
        if (courses != null && !courses.isEmpty()) {
            for (Course c : courses) {
    %>
    <li>
        <a href="course?id=<%= c.getCourseId() %>"><%= c.getName() %> - <%= c.getDescription() %></a>
        <form action="course" method="post">
            <input type="hidden" name="courseId" value="<%= c.getCourseId() %>" />
            <input type="submit" value="Register">
        </form>
    </li>
    <% } } %>
</ul>
</body>
</html>