<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form action="login" method="post">
    <% if ("invalid".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Invalid username or password.</p>
    <% } %>
    <label for="firstname">Firstname:</label>
    <input type="text" id="firstname" name="firstname" required>

    <label for="lastname">Lastname:</label>
    <input type="text" id="lastname" name="lastname" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <input type="submit" value="Continue">
</form>
</body>
</html>
