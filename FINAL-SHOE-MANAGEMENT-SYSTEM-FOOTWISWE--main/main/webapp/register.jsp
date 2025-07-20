<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Footwise</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>

<form action="register" method="post">
    <h2>Register at Footwise</h2>

    <input type="text" name="username" placeholder="Enter username" required>
    <input type="password" name="password" placeholder="Enter password" required>

    <select name="role" required>
        <option value="">Select Role</option>
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select>

    <button type="submit">Register</button>

    <a href="login.jsp">Already have an account? Login</a>

    <p style="color: red;">${errorMessage}</p>
    <p style="color: green;">${successMessage}</p>
</form>

</body>
</html>
