<%@ page import="com.example.coursesSystem.models.Teacher" %>
<%@ page import="com.example.coursesSystem.models.User" %>
<%@ page import="com.example.coursesSystem.repositories.DBTeacherUtils" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Integer teacherId = ((User) session.getAttribute("user")).getId();
    Integer teacherPageId = null;
    if((Teacher) request.getAttribute("teacherInfo") != null){
        teacherPageId = ((Teacher) request.getAttribute("teacherInfo")).getId();
    } else {
        teacherPageId = Integer.parseInt(request.getParameter("teacherId"));
        Connection con = (Connection) session.getAttribute("connection");
        try {
            Teacher teacherInfo = DBTeacherUtils.findTeacherById(con, teacherPageId);
            request.setAttribute("teacherInfo", teacherInfo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
<p><strong>Degree:</strong> ${teacherInfo.getQualification().name().toUpperCase()}</p>
<p><strong>Experience (Years):</strong> ${teacherInfo.getExperience()}</p>


<% if (teacherId != null && teacherId.equals(teacherPageId)) { %>
<a href="teacher-update.jsp?teacherId=${teacherInfo.getId()}">Edit Account</a>
<% } %>
</body>
</html>
