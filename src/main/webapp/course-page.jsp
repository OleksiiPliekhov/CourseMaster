<%@ page import="com.example.coursesSystem.models.Course" %>
<%@ page import="com.example.coursesSystem.models.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.coursesSystem.repositories.DBCourseUtils" %>
<%@ page import="java.sql.SQLException" %><%--
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
<p><strong>Price:</strong> ${course.price}</p>
<p><strong>Teacher: </strong> <a href="teacher-page.jsp?teacherId=${teacher.getId()}">${teacher.getFirstname()} ${teacher.getLastname()} (${teacher.getQualification().name()})</a></p>


<% if (userId != null && userId.equals(teacherId)) { %>
<a href="course-update.jsp?courseId=${course.courseId}">Edit Course</a>
<% }
else {
    try {
        if (DBCourseUtils.isUserRegisteredOnCourse((Connection) session.getAttribute("connection"), ((Course)request.getAttribute("course")).getCourseId(), userId)) {%>
<form action="leaveCourse" method="post">
    <input type="hidden" name="courseId" value="${course.courseId}" />
    <input type="submit" value="Leave">
</form>
        <% }else {%>
        <form action="course" method="post">
            <input type="hidden" name="courseId" value="${course.courseId}" />
            <input type="submit" value="Register">
        </form>
        <%}
} catch (SQLException e) {
    throw new RuntimeException(e);
}
}
%>

</body>
</html>
