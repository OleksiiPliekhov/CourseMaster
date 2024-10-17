<%--
  Created by IntelliJ IDEA.
  User: aleks
  Date: 10/18/2024
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update form</title>
</head>
<body>
<h1>Update Your Information</h1>
<% if ("invalid".equals(request.getParameter("updateError"))) { %>
<p style="color:red;">Something went wrong, try again</p>
<% } %>
<form action="user" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name" value="${user.name}" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="${user.email}" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Update Info">
</form>
</body>
</html>
