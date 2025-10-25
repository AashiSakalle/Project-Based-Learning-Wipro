package com.apauctions.model;

import java.io.Serializable;

public class BidBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Fields matching form inputs
    private String itemId;
    private String itemName;
    private String yourName;
    private String emailAddress;
    private double amountBid;
    private boolean autoIncrement;

    // Default Constructor (required by JavaBean spec)
    public BidBean() {
    }

    // Getters and Setters

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getAmountBid() {
        return amountBid;
    }

    // Note: The String parameter from request is converted to double here.
    public void setAmountBid(double amountBid) {
        this.amountBid = amountBid;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}