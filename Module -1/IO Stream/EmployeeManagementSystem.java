import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagementSystem {

    // Define the file name where employee data will be stored
    private static final String DATA_FILE = "employee_data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            try {
                // Read the user's choice
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        addEmployee(scanner);
                        break;
                    case 2:
                        displayAllEmployees();
                        break;
                    case 3:
                        System.out.println("3");
                        System.out.println("Exiting the System");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
                choice = 0; // Set choice to non-exit value
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                choice = 0;
            }
        } while (choice != 3);

        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Add an Employee");
        System.out.println("2. Display All");
        System.out.println("3. Exit");
    }

    /**
     * Gathers employee details from the user and appends them to the data file.
     * @param scanner The Scanner object for reading user input.
     */
    private static void addEmployee(Scanner scanner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE, true))) {
            
            System.out.print("Enter Employee ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Employee Age: ");
            String age = scanner.nextLine();
            
            System.out.print("Enter Employee Salary: ");
            // Assuming salary can be an integer or a double for flexibility
            String salary = scanner.nextLine(); 
            
            // The prompt requests employee ID, designation, and salary.
            // Based on the sample output, the fields seem to be ID, Name, Age, Salary.
            // I will use ID, Name, Age, and Salary to match the sample.
            
            // Format: ID Name Age Salary
            writer.println(id + "\t" + name + "\t" + age + "\t" + salary);
            System.out.println("Employee added successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and displays all employee details from the data file.
     */
    private static void displayAllEmployees() {
        File file = new File(DATA_FILE);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No employee data found.");
            return;
        }

        System.out.println("2"); // Matches the sample output when option 2 is selected
        System.out.println("----Report----");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // The data is tab-separated, but the sample output doesn't show tabs.
                // We'll replace tabs with a space for neat output.
                String displayLine = line.replace('\t', ' ');
                System.out.println(displayLine); 
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        System.out.println("----End of Report----");
    }
}