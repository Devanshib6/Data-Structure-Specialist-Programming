<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Footwise</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<form action="login" method="post">
    <h2>Login to Footwise</h2>

    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>

    <button type="submit">Login</button>

    <a href="register.jsp">Don't have an account? Register here</a>

    <p>${errorMessage}</p>
</form>

</body>
</html>
