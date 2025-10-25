// src/main/java/com/example/model/Employee.java

package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Handles auto-generation of ID
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary")
    private double salary;

    @Column(name = "city")
    private String city;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_no")
    private String mobileNo;

    // Default Constructor
    public Employee() {
    }

    // Constructor without ID (for saving new employees)
    public Employee(String name, String gender, String designation, double salary, String city, String emailId, String mobileNo) {
        this.name = name;
        this.gender = gender;
        this.designation = designation;
        this.salary = salary;
        this.city = city;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    // Getters and Setters (omitted for brevity, but required for Hibernate)

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    // ... all other getters/setters
}