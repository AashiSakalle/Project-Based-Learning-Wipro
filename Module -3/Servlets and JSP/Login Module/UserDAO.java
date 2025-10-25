package com.app.model;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    // Simulated database: stores <username, password>
    private static final Map<String, String> userDatabase = new HashMap<>();

    static {
        // Seed initial data
        userDatabase.put("admin", "pass123");
        userDatabase.put("user1", "userpass");
    }

    // Check if user exists and credentials are valid
    public boolean validateUser(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    // Register a new user
    public boolean registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return false; // User already exists
        }
        userDatabase.put(username, password);
        return true;
    }

    // Change password for an existing user
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (validateUser(username, oldPassword)) {
            userDatabase.put(username, newPassword);
            return true;
        }
        return false; // Invalid username or old password
    }
}