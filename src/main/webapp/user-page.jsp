<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursesSystem.UserBean" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<!-- Инициализация JavaBean -->
<jsp:useBean id="user" class="com.example.coursesSystem.UserBean" scope="request" />

<!-- Передача значений из атрибута sessionUser в JavaBean user -->
<jsp:setProperty name="user" property="firstname" value="${userInfo.firstname}" />
<jsp:setProperty name="user" property="lastname" value="${userInfo.lastname}" />
<jsp:setProperty name="user" property="password" value="${userInfo.password}" />
<jsp:setProperty name="user" property="balance" value="${userInfo.balance}" />

<!-- Вывод значений через getProperty -->
<p><strong>First Name:</strong> <jsp:getProperty name="user" property="firstname" /></p>
<p><strong>Last Name:</strong> <jsp:getProperty name="user" property="lastname" /></p>
<p><strong>Password:</strong> <jsp:getProperty name="user" property="password" /></p>
<p><strong>Balance:</strong> <jsp:getProperty name="user" property="balance" /></p>
</body>
</html>
