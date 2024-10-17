<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.coursessystem.beans.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<
<html>
<head>
    <title>Courses Page</title>
</head>
<body>
<h1>All Courses</h1>
<ul>
    <c:forEach var="course" items="${courses}">
        <li>
            <a href="course?id=${course.getCourseId()}">${course.getName()} - ${course.getDescription()}</a>
            <form action="course" method="post">
                <input type="hidden" name="courseId" value="${course.getCourseId()}" />
                <input type="submit" value="Register">
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>