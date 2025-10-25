package com.app.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // retypePassword validation is primarily done on the client side

        if (userDAO.registerUser(username, password)) {
            // Success: redirect to Login Page with a success message
            request.setAttribute("message", "Registration successful! You can now log in.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.forward(request, response);
        } else {
            // Failure: username already exists
            request.setAttribute("message", "Registration failed: Username already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.html");
            dispatcher.forward(request, response);
        }
    }
}