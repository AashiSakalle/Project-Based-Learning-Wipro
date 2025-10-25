package com.apauctions.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apauctions.model.BidBean; // Import the JavaBean

@WebServlet("/BidServlet")
public class BidServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Create and populate the JavaBean
        BidBean bid = new BidBean();
        
        try {
            // Get form parameters
            String itemId = request.getParameter("itemId");
            String itemName = request.getParameter("itemName");
            String yourName = request.getParameter("yourName");
            String emailAddress = request.getParameter("emailAddress");
            // Parse amountBid to a double
            double amountBid = Double.parseDouble(request.getParameter("amountBid"));
            // Check if the checkbox was checked (its value is "true" if checked, null otherwise)
            boolean autoIncrement = "true".equals(request.getParameter("autoIncrement"));

            // Set the JavaBean properties
            bid.setItemId(itemId);
            bid.setItemName(itemName);
            bid.setYourName(yourName);
            bid.setEmailAddress(emailAddress);
            bid.setAmountBid(amountBid);
            bid.setAutoIncrement(autoIncrement);
            
            // 2. Store the JavaBean in the request scope
            request.setAttribute("bidDetails", bid);

            // 3. Forward to the JSP page for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bid_success.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            // Handle case where Amount Bid is not a valid number (though HTML validation helps)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bid amount submitted.");
        } 
        // Note: HTML 'required' attribute handles empty fields, so no explicit server-side check is strictly needed 
        // unless you want more advanced validation.
    }
}