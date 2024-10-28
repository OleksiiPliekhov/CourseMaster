<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Page</title>
</head>
<body>
<h1>Teacher Information</h1>

<p><strong>First Name:</strong> ${teacherInfo.getFirstname()}</p>
<p><strong>Last Name:</strong> ${teacherInfo.getLastname()}</p>
<p><strong>Password:</strong> ${teacherInfo.getPassword()}</p>
<p><strong>Degree:</strong> ${teacherInfo.getDegree().name().toUpperCase()}</p>
<p><strong>Experience (Years):</strong> ${teacherInfo.getExperienceYears()}</p>

</body>
</html>
