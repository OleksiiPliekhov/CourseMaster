<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Page</title>
</head>
<body>
<h1>Teacher Information</h1>

<p><strong>First Name:</strong> ${teacher.getFirstname()}</p>
<p><strong>Last Name:</strong> ${teacher.getLastname()}</p>
<p><strong>Degree:</strong> ${teacher.getDegree().name().toUpperCase()}</p>
<p><strong>Experience (Years):</strong> ${teacher.getExperienceYears()}</p>

</body>
</html>
