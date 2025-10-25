<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; padding-top: 50px; }
        .welcome-message { color: #28a745; font-size: 2em; margin-bottom: 20px; }
        .change-link a { 
            color: #007bff; 
            text-decoration: none; 
            font-size: 1.1em;
            border-bottom: 2px solid #007bff;
            padding-bottom: 2px;
        }
        .change-link a:hover {
            color: #0056b3;
            border-bottom-color: #0056b3;
        }
    </style>
</head>
<body>
    <% 
        // Retrieve userId from the session
        String userId = (String) session.getAttribute("userId");
        
        // IMPORTANT: Security Check - if the session attribute is missing, redirect to login.
        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }
    %>
    
    <div class="welcome-message">
        Welcome : <%= userId %>
    </div>
    
    <div class="change-link">
        <a href="changePassword.html">Change Password</a>
    </div>

    <!-- Optional: Logout link -->
    <p style="margin-top: 30px;">
        <a href="LogoutServlet" style="color: #d9534f;">Logout</a>
    </p>

</body>
</html>