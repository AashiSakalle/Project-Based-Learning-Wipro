package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotBlank(message = "Username is mandatory.")
    private String username;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 6, max = 6, message = "Password must be exactly 6 characters.")
    private String password;

    @NotBlank(message = "Employee number is mandatory.")
    @Pattern(regexp = "^[A-Za-z]\\d{4}$", message = "Employee ID must be one letter followed by 4 digits.")
    private String employeeNumber;

    @NotBlank(message = "Email address is mandatory.")
    @Email(message = "Must be a valid email address.")
    private String emailAddress;

    public User() {
    }

    // Getters and Setters

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmployeeNumber() { return employeeNumber; }
    public void setEmployeeNumber(String employeeNumber) { this.employeeNumber = employeeNumber; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}