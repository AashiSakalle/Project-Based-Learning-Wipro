<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        .success-message { color: green; font-size: 1.5em; margin-top: 50px; }
    </style>
</head>
<body>

    <div class="success-message">
        <h1>Welcome, ${sessionScope.username}!</h1>
        <p>You have successfully logged into the application.</p>
    </div>
    
    <p><a href="index.html">Logout / Back to Login</a></p>

</body>
</html>