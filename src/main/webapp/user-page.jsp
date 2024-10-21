<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursessystem.beans.User" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<p><strong>First Name:</strong> <%= user.getFirstname() %></p>
<p><strong>Last Name:</strong> <%= user.getLastname() %></p>
<p><strong>Balance:</strong> <%= user.getBalance() %></p>

</body>
</html>
