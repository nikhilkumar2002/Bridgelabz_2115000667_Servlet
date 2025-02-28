<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h3>Hi <%= request.getAttribute("user") %>, Login successful.</h3>
    <a href="login.html">Logout</a>
</body>
</html>
