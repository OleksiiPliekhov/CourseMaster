<%@ page import="com.example.coursesSystem.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Course</title>
</head>
<body>
    <h1>Create a New Course</h1>
    <form action="courses" method="post">
        <input type="hidden" name="teacherId" value="<%= ((User)session.getAttribute("user")).getId() %>">
        <label for="name">Course Name:</label><br>
        <input type="text" id="name" name="name" required><br><br>

        <label for="description">Course Description:</label><br>
        <input type="text" id="description" name="description" required></input><br><br>

        <label for="maxStudentsAmount">Max Students Amount:</label><br>
        <input type="number" id="maxStudentsAmount" name="maxStudentsAmount" min="1" required><br><br>

        <label for="price">Course Price:</label><br>
        <input type="number" id="price" name="price" step="0.01" required><br><br>

        <button type="submit">Create Course</button>
    </form>
</body>
</html>