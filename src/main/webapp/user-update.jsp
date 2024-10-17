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

<!--
<%--@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Update form</title>
</head>
<body>
<h1>Update Your Information</h1>

<%-- Проверка на наличие ошибки в параметре --%>
<%-- if ("invalid".equals(request.getAttribute("updateError"))) { %>
<p style="color:red;">Something went wrong, try again</p>
<% } %>

<form action="user" method="post">
<label for="name">Name:</label><br>
<input type="text" id="name" name="name"
value="<%= (request.getAttribute("firstname") != null) ? request.getAttribute("firstname") : user.getFirstname() %>"
required><br><br>

<label for="email">Email:</label><br>
<input type="email" id="email" name="email"
value="<%= (request.getAttribute("email") != null) ? request.getAttribute("email") : user.getEmail() %>"
required><br><br>

<label for="password">Password:</label><br>
<input type="password" id="password" name="password"
value="<%= (request.getAttribute("password") != null) ? request.getAttribute("password") : "" %>"
required><br><br>

<input type="submit" value="Update Info">
</form>
</body>
</html>

-->
