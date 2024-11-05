<%@ page import="java.sql.Connection" %>
<%@ page import="com.example.coursesSystem.utils.DBUserUtils" %>
<%@ page import="com.example.coursesSystem.models.User" %><%--
  Created by IntelliJ IDEA.
  User: aleks
  Date: 10/18/2024
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    int userId = Integer.parseInt(request.getParameter("userId"));
    Connection con = (Connection) session.getAttribute("connection");
    User user = DBUserUtils.findUserById(con, userId);
    if (user == null) {
        out.println("Course not found");
        return;
    }
%>

<html>
<head>
    <title>Update form</title>
</head>
<body>
<h1>Update Your Information</h1>
<% if ("invalid".equals(request.getParameter("updateError"))) { %>
<p style="color:red;">Something went wrong, try again</p>
<% } %>
<form action="userUpdate" method="post">
    <input type="hidden" name="userId" value="<%= user.getId() %>">
    <label for="firstname">Name:</label><br>
    <input type="text" id="firstname" name="firstname" value="${user.getFirstname()}" ><br><br>

    <label for="lastname">Name:</label><br>
    <input type="text" id="lastname" name="lastname" value="${user.getLastname()}" ><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" value="${user.getPassword()}" ><br><br>

    <input type="submit" value="Update Info">
</form>
</body>
</html>




