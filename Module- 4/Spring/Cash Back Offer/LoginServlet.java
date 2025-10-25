package com.legionbank.servlet;

import com.legionbank.dao.BankDao;
import com.legionbank.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bankLogin")
public class LoginServlet extends HttpServlet {
    private BankDao bankDao = new BankDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerID = request.getParameter("customerID");
        String password = request.getParameter("password");
        
        Customer customer = bankDao.getCustomer(customerID);

        if (customer != null && customer.getPassword().equals(password)) {
            // Success: Store CustomerID and Balance in Session
            request.getSession().setAttribute("customerID", customer.getCustomerID());
            request.getSession().setAttribute("currentBalance", customer.getBalance());
            
            // Redirect to the Claim page (Your Current Balance is: XXXX)
            response.sendRedirect("claim.jsp");
        } else {
            // Failure
            request.setAttribute("error", "Invalid Customer ID or Password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}