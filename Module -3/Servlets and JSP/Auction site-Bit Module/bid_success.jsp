<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bid Submitted</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .success-box {
            width: 400px; 
            margin: 50px auto; 
            background-color: #ffffcc; /* Light yellow background */
            border: 1px solid #e6e600; 
            padding: 20px; 
            text-align: center;
        }
        .details-table {
            width: 100%;
            margin-top: 15px;
            border-collapse: collapse;
        }
        .details-table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        .details-table td:first-child {
            font-weight: bold;
            width: 50%;
        }
    </style>
</head>
<body>

<%-- Retrieve the JavaBean from the request scope using EL/JSTL --%>
<%-- The bean was set in the servlet with request.setAttribute("bidDetails", bid) --%>

<div class="success-box">
    <h3>Bid Submitted</h3>
    <p>Your bid is now active. If your bid is successful, you will be notified within 24 hours of the close of bidding.</p>
    
    <div class="details-table">
        <table>
            <tr><td>Item ID:</td><td>${bidDetails.itemId}</td></tr>
            <tr><td>Name:</td><td>${bidDetails.yourName}</td></tr>
            <tr><td>Email address:</td><td>${bidDetails.emailAddress}</td></tr>
            <tr>
                <td>Bid price:</td>
                <td>
                    Rs. <fmt:formatNumber value="${bidDetails.amountBid}" pattern="#,##0.00" />
                </td>
            </tr>
            <tr><td>Auto-increment price:</td><td>${bidDetails.autoIncrement}</td></tr>
        </table>
    </div>
</div>

</body>
</html>