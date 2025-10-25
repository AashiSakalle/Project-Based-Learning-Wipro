<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Operations Menu</title>
</head>
<body>
    <h1>Employee Management System</h1>
    
    <% if (request.getAttribute("message") != null) { %>
        <p style="color: green;"><%= request.getAttribute("message") %></p>
    <% } %>

    <ul>
        <li><a href="addEmployee">ADD Employee</a></li>
        <li><a href="deleteEmployeeForm">DELETE Employee</a></li>
        <li><a href="modifyDetails">MODIFY Personal details</a> (Uses session ID)</li>
        <li><a href="selectEmployeeByIdForm">SELECT Employee by Id</a></li>
        <li><a href="selectAllEmployees">SELECT ALL Employee</a></li>
    </ul>
    
    <br>
    <a href="logout">Logout</a>
</body>
</html>