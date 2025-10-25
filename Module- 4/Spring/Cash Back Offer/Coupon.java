package com.legionbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CouponTable")
public class Coupon implements Serializable {

    @Id
    @Column(name = "couponCode")
    private String couponCode;

    @Column(name = "offerPercentage")
    private int offerPercentage;

    // Constructors
    public Coupon() {}

    // Getters and Setters

    public String getCouponCode() { return couponCode; }
    public void setCouponCode(String couponCode) { this.couponCode = couponCode; }

    public int getOfferPercentage() { return offerPercentage; }
    public void setOfferPercentage(int offerPercentage) { this.offerPercentage = offerPercentage; }
}