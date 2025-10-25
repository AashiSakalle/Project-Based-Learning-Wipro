package com.example.dao;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // JpaRepository provides: save(), findById(), findAll(), deleteById(), etc.
    
    // Optional: Add custom queries if needed, but not required for basic CRUD
    Optional<Employee> findByEmailId(String emailId);
}