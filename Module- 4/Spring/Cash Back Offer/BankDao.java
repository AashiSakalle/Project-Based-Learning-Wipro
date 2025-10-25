package com.legionbank.dao;

import com.legionbank.model.Customer;
import com.legionbank.model.Coupon;
import com.legionbank.util.HibernateUtil; // Assuming utility class exists
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankDao {

    // --- Customer Login ---
    public Customer getCustomer(String customerID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, customerID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- Coupon Retrieval ---
    public Coupon getCoupon(String couponCode) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Coupon.class, couponCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- Update Balance ---
    public boolean updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}