package com.legionbank.servlet;

import com.legionbank.dao.BankDao;
import com.legionbank.model.Customer;
import com.legionbank.model.Coupon;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/claimOffer")
public class ClaimServlet extends HttpServlet {
    private BankDao bankDao = new BankDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String customerID = (String) session.getAttribute("customerID");
        String couponCode = request.getParameter("couponCode");
        
        // Ensure user is logged in
        if (customerID == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        Customer customer = bankDao.getCustomer(customerID);
        Coupon coupon = bankDao.getCoupon(couponCode);
        
        if (coupon == null) {
            // Error: Coupon not found
            request.setAttribute("error", "Invalid Coupon Code entered.");
            request.getRequestDispatcher("claim.jsp").forward(request, response);
            return;
        }

        // --- Calculation Logic ---
        double currentBalance = customer.getBalance();
        int offerPercentage = coupon.getOfferPercentage();
        
        double cashBackAmount = currentBalance * (offerPercentage / 100.0);
        double newBalance = currentBalance + cashBackAmount;

        // --- Database Update ---
        customer.setBalance(newBalance);
        boolean success = bankDao.updateCustomer(customer);
        
        if (success) {
            // Update session balance and set result attributes
            session.setAttribute("currentBalance", newBalance);
            
            request.setAttribute("cashBackPercentage", offerPercentage + "%");
            request.setAttribute("updatedBalance", String.format("%.2f", newBalance));
            request.setAttribute("cashBackAmount", String.format("%.2f", cashBackAmount));
            
            request.getRequestDispatcher("congratulations.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "An error occurred during transaction update.");
            request.getRequestDispatcher("claim.jsp").forward(request, response);
        }
    }
}