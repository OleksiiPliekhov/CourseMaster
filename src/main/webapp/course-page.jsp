<%@ page import="com.example.coursesSystem.models.Course" %>
<%@ page import="com.example.coursesSystem.models.User" %><%--
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
<%@ include file="header.jsp" %>
<h1>${course.name}</h1>
<p><strong>Description:</strong> ${course.description}</p>
<p><strong>Max Students:</strong> ${course.maxStudentsAmount}</p>
<p><strong>Teacher: </strong> <a href="teacher-page.jsp?teacherId=${teacher.getId()}">${teacher.getFirstname()} ${teacher.getLastname()} (${teacher.getQualification().name()})</a></p>


<% if (userId != null && userId.equals(teacherId)) { %>
<a href="course-update.jsp?courseId=${course.courseId}">Edit Course</a>
<% } else {%>
<form action="course" method="post">
    <input type="hidden" name="courseId" value="${course.courseId}" />
    <input type="submit" value="Register">
</form>
<%}%>

</body>
</html>
