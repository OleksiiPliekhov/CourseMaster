<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        /* Optional: Style to hide teacher fields initially */
        #teacherFields {
            display: none;
        }
    </style>
    <script>
        function toggleTeacherFields() {
            const role = document.getElementById("role").value;
            const teacherFields = document.getElementById("teacherFields");

            if (role === "teacher") {
                teacherFields.style.display = "block";
            } else {
                teacherFields.style.display = "none";
            }
        }
    </script>
</head>
<body>
<h3>Registration</h3>
<form action="register" method="post">
    <% if ("invalid".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Some fields are incorrect.</p>
    <% } %>
    <label for="firstname">Firstname:</label>
    <input type="text" id="firstname" name="firstname" required>

    <label for="lastname">Lastname:</label>
    <input type="text" id="lastname" name="lastname" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="role">Register as:</label>
    <select id="role" name="role" onchange="toggleTeacherFields()">
        <option value="user">User</option>
        <option value="teacher">Teacher</option>
    </select>

    <!-- Additional fields for teacher -->
    <div id="teacherFields">
        <label for="qualification">Qualification:</label>
        <select id="qualification" name="qualification">
            <option value="BACHELOR">Bachelor's Degree</option>
            <option value="MASTER">Master's Degree</option>
            <option value="PHD">PhD</option>
        </select>

        <label for="experience">Experience:</label>
        <input type="number" id="experience" name="experience" min="0">
    </div>

    <button type="submit">Register</button>
</form>

</body>
</html>
