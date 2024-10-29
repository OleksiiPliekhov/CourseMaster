<%@ page import="com.example.coursesSystem.beans.Course" %>
<%@ page import="com.example.coursesSystem.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: aleks
  Date: 10/17/2024
  Time: 7:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Integer userId = ((User) session.getAttribute("user")).getId();
    Integer teacherId = ((Course)request.getAttribute("course")).getTeacherId();
%>
<html>
<head>
    <title>Course Page</title>
</head>
<body>
<h1>${course.name}</h1>
<p><strong>Description:</strong> ${course.description}</p>
<p><strong>Max Students:</strong> ${course.maxStudentsAmount}</p>
<p><strong>Teacher: </strong> <a href="teacher-page.jsp">${teacher.getFirstname()} ${teacher.getLastname()} (${teacher.getDegree().name()})</a></p>


<% if (userId != null && userId.equals(teacherId)) { %>
<a href="course-update.jsp?courseId=${course.courseId}">Edit Course</a>
<% } %>


</body>
</html>
