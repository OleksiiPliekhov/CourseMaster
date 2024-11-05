<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursesSystem.models.Course" %>
<%@ page import="com.example.coursesSystem.utils.DBCourseUtils" %>
<%@ page import="java.sql.Connection" %>

<%
    int courseId = Integer.parseInt(request.getParameter("courseId"));
    Connection con = (Connection) session.getAttribute("connection");
    Course course = DBCourseUtils.findCourseById(con, courseId);
    if (course == null) {
        out.println("Course not found");
        return;
    }
%>

<html>
<head>
    <title>Update Course</title>
</head>
<body>
<h1>Update Course</h1>
<form action="courseUpdate" method="post">
    <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
    <p>
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="<%= course.getName() %>">
    </p>
    <p>
        <label for="description">Description:</label>
        <textarea name="description" id="description"><%= course.getDescription() %></textarea>
    </p>
    <p>
        <label for="maxStudentsAmount">Max Students:</label>
        <input type="number" name="maxStudentsAmount" id="maxStudentsAmount" value="<%= course.getMaxStudentsAmount() %>">
    </p>
    <p>
        <label for="price">Price:</label>
        <textarea name="price" id="price"><%= course.getPrice() %></textarea>
    </p>
    <p>
        <input type="submit" value="Update Course">
    </p>
</form>
</body>
</html>
