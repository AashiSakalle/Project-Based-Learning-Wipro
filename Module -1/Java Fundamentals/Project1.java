import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Project1: A small Java application to process employee data, calculate salary,
 * and display information based on an Employee ID provided as a command-line argument.
 */
public class Project1 {

    // Helper class to represent an Employee record
    private static class Employee {
        int empNo;
        String empName;
        String joinDateStr; // Storing as String for initialization, but can be parsed later if needed
        char designationCode;
        String department;
        double basic;
        double hra;
        double it;

        public Employee(int empNo, String empName, String joinDateStr, char designationCode, String department, double basic, double hra, double it) {
            this.empNo = empNo;
            this.empName = empName;
            this.joinDateStr = joinDateStr;
            this.designationCode = designationCode;
            this.department = department;
            this.basic = basic;
            this.hra = hra;
            this.it = it;
        }
    }

    public static void main(String[] args) {
        // 1. Check for command-line argument (Employee ID)
        if (args.length == 0) {
            System.out.println("Error: Please provide an Employee ID as a command-line argument.");
            System.out.println("Usage: java Project1 <EmployeeID>");
            return;
        }

        int targetEmpNo;
        try {
            targetEmpNo = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: The provided argument is not a valid Employee ID (must be an integer).");
            return;
        }

        // --- Data Initialization ---

        // Employee Data: Initialize an array of Employee objects
        Employee[] employees = new Employee[]{
            new Employee(1001, "Ashish", "01/04/2009", 'e', "R&D", 20000, 8000, 3000),
            new Employee(1002, "Sushma", "23/08/2012", 'c', "PM", 30000, 12000, 9000),
            new Employee(1003, "Rahul", "12/11/2008", 'k', "Acct", 10000, 8000, 1000),
            new Employee(1004, "Chahat", "29/01/2013", 'r', "Front Desk", 12000, 6000, 2000),
            new Employee(1005, "Ranjan", "16/07/2005", 'm', "Engg", 50000, 20000, 20000),
            new Employee(1006, "Suman", "1/1/2000", 'e', "Manufacturing", 23000, 9000, 4400),
            new Employee(1007, "Tanmay", "12/06/2006", 'c', "PM", 29000, 12000, 10000)
        };

        // Dearness Allowance (DA) and Designation Map for easy lookup
        // Although the prompt implies using switch-case, a map is often cleaner
        // for lookups. We'll use the switch-case for the logic as requested.
        // The switch-case logic will be implemented inside the loop.

        // --- Processing ---

        Employee foundEmployee = null;
        for (Employee emp : employees) {
            if (emp.empNo == targetEmpNo) {
                foundEmployee = emp;
                break; // Employee found, exit loop
            }
        }

        // 2. Handle the case where the employee is not found
        if (foundEmployee == null) {
            System.out.println("There is no employee with empid: " + targetEmpNo);
            return;
        }

        // 3. Calculate Salary
        String designation = "";
        double da = 0.0;

        // Use switch-case to determine Designation and DA based on Designation Code
        // (Note 2: Use switch-case to print Designation and to find the value of DA)
        switch (foundEmployee.designationCode) {
            case 'e':
                designation = "Engineer";
                da = 20000;
                break;
            case 'c':
                designation = "Consultant";
                da = 32000;
                break;
            case 'k':
                designation = "Clerk";
                da = 12000;
                break;
            case 'r':
                designation = "Receptionist";
                da = 15000;
                break;
            case 'm':
                designation = "Manager";
                da = 40000;
                break;
            default:
                designation = "Unknown";
                da = 0.0;
                System.out.println("Warning: Unknown designation code: " + foundEmployee.designationCode);
        }

        // Salary Calculation (Note 1: Salary = (Basic + HRA + DA - IT))
        double salary = foundEmployee.basic + foundEmployee.hra + da - foundEmployee.it;

        // 4. Display the output
        System.out.println("Emp No. Emp Name\tDepartment\tDesignation\tSalary");
        System.out.println("-----------------------------------------------------------------");
        // Print the details
        System.out.printf("%-7d %-9s\t%-10s\t%-11s\t%.2f%n",
                          foundEmployee.empNo,
                          foundEmployee.empName,
                          foundEmployee.department,
                          designation,
                          salary);
    }
}