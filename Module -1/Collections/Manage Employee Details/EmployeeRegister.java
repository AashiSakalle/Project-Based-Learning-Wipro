import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class to represent an Employee. 
 * This class uses Generics implicitly as it's a strongly-typed data structure.
 */
class Employee {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String address;

    // Constructor
    public Employee(String firstName, String lastName, String mobileNumber, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
    }

    // Getter for sorting (required by the Comparator)
    public String getFirstName() {
        return firstName;
    }
    
    // Getters for display
    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}

public class EmployeeRegister {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get the number of employees
        System.out.print("Enter the Number of Employees: ");
        int numEmployees;
        try {
            numEmployees = scanner.nextInt();
            // Consume the newline character left after reading the integer
            scanner.nextLine(); 
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for the count.");
            scanner.close();
            return;
        }
        
        System.out.println(numEmployees); // Echo the input as per sample

        // Use Generics: A list specifically for Employee objects
        List<Employee> employeeList = new ArrayList<>();

        // 2. Collect employee details
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("\nEnter Employee " + (i + 1) + " Details:");
            
            System.out.print("Enter the Firstname: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Enter the Lastname: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Enter the Mobile: ");
            String mobileNumber = scanner.nextLine();
            
            System.out.print("Enter the Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter the Address: ");
            String address = scanner.nextLine();

            // Create new Employee object and add to the list
            Employee emp = new Employee(firstName, lastName, mobileNumber, email, address);
            employeeList.add(emp);
        }

        // 3. Sort the collection by First Name
        // This uses an anonymous inner class for the Comparator
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                // Compares the first names alphabetically
                return emp1.getFirstName().compareTo(emp2.getFirstName());
            }
        });

        // 4. Print the sorted employee details
        System.out.println("\nEmployee List:");
        
        // Header using format specifiers
        // "%-15s" for left-justified string, width 15
        String headerFormat = "%-15s %-15s %-15s %-30s %-15s%n";
        System.out.format(headerFormat, "FirstName", "SecondName", "MobileNumber", "Email", "Address");
        
        // Print the data
        for (Employee emp : employeeList) {
            System.out.format(
                headerFormat,
                emp.getFirstName(),
                emp.getLastName(),
                emp.getMobileNumber(),
                emp.getEmail(),
                emp.getAddress()
            );
        }

        scanner.close();
    }
}