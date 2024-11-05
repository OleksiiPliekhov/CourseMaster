<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursesSystem.UserBean" %>
<%@ page import="com.example.coursesSystem.models.User" %>


<%
    Integer userId = ((User) session.getAttribute("user")).getId();
    Integer userPageId = ((User) request.getAttribute("userInfo")).getId();

%>

<html>
<head>
    <title>User Page</title>
</head>
<body>
<%@ include file="header.jsp" %>

<jsp:useBean id="user" class="com.example.coursesSystem.UserBean" scope="request" />

<jsp:setProperty name="user" property="firstname" value="${userInfo.firstname}" />
<jsp:setProperty name="user" property="lastname" value="${userInfo.lastname}" />
<jsp:setProperty name="user" property="password" value="${userInfo.password}" />
<jsp:setProperty name="user" property="balance" value="${userInfo.balance}" />

<!-- Вывод значений через getProperty -->
<p><strong>First Name:</strong> <jsp:getProperty name="user" property="firstname" /></p>
<p><strong>Last Name:</strong> <jsp:getProperty name="user" property="lastname" /></p>
<p><strong>Password:</strong> <jsp:getProperty name="user" property="password" /></p>
<p><strong>Balance:</strong> <jsp:getProperty name="user" property="balance" /></p>


<a href="participatedCourses.jsp?userId=${userInfo.getId()}">Participate in courses</a>

<% if (userId != null && userId.equals(userPageId)) { %>
<a href="user-update.jsp?userId=${userInfo.getId()}">Edit Account</a>
<% } %>

<a href="deposite.jsp">Deposite</a>
</body>
</html>
