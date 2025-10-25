package com.app.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.UserDAO;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String retypeNewPassword = request.getParameter("retypeNewPassword");

        // Server-side validation check (in addition to client-side)
        if (!newPassword.equals(retypeNewPassword)) {
            request.setAttribute("message", "Error: New password and retyped password do not match.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.html");
            dispatcher.forward(request, response);
            return;
        }

        // Check old credentials and update password
        if (userDAO.changePassword(username, oldPassword, newPassword)) {
            // Success: redirect to Login Page
            request.setAttribute("message", "Password successfully updated! Please log in with your new password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.forward(request, response);
        } else {
            // Failure: Invalid username or old password
            request.setAttribute("message", "Error: Invalid Username or Old Password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.html");
            dispatcher.forward(request, response);
        }
    }
}