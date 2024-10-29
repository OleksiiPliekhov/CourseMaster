<%@ page import="com.example.coursesSystem.beans.Teacher" %>
<%@ page import="com.example.coursesSystem.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Integer teacherId = ((User) session.getAttribute("user")).getId();
    Integer teacherPageId = ((Teacher) request.getAttribute("teacherInfo")).getId();

%>


<html>
<head>
    <title>Teacher Page</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Teacher Information</h1>

<p><strong>First Name:</strong> ${teacherInfo.getFirstname()}</p>
<p><strong>Last Name:</strong> ${teacherInfo.getLastname()}</p>
<p><strong>Password:</strong> ${teacherInfo.getPassword()}</p>
<p><strong>Degree:</strong> ${teacherInfo.getDegree().name().toUpperCase()}</p>
<p><strong>Experience (Years):</strong> ${teacherInfo.getExperienceYears()}</p>


<% if (teacherId != null && teacherId.equals(teacherPageId)) { %>
<a href="teacher-update.jsp?teacherId=${teacherPageId}">Edit Account</a>
<% } %>
</body>
</html>
