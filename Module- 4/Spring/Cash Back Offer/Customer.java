package com.legionbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CustomerTable")
public class Customer implements Serializable {

    @Id
    @Column(name = "customerID")
    private String customerID;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

    // Constructors
    public Customer() {}
    
    // Getters and Setters

    public String getCustomerID() { return customerID; }
    public void setCustomerID(String customerID) { this.customerID = customerID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}