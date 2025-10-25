import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StringListOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // The core data structure for storing the String objects
        List<String> itemList = new ArrayList<>();
        int choice = 0;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");

            try {
                // Read the user's menu choice
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()

                switch (choice) {
                    case 1:
                        insertItem(scanner, itemList);
                        break;
                    case 2:
                        searchItem(scanner, itemList);
                        break;
                    case 3:
                        deleteItem(scanner, itemList);
                        break;
                    case 4:
                        displayList(itemList);
                        break;
                    case 5:
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for your choice.");
                scanner.nextLine(); // Clear the buffer
                choice = 0; // Prevent accidental exit
            }

        } while (choice != 5);

        scanner.close();
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMenu() {
        System.out.println("\n1. Insert");
        System.out.println("2. Search");
        System.out.println("3. Delete");
        System.out.println("4. Display");
        System.out.println("5. Exit");
    }

    /**
     * Inserts an item into the list.
     */
    private static void insertItem(Scanner scanner, List<String> itemList) {
        System.out.print("Enter the item to be inserted: ");
        String item = scanner.nextLine();
        itemList.add(item);
        System.out.println("Inserted successfully");
    }

    /**
     * Searches for an item in the list and reports the result.
     */
    private static void searchItem(Scanner scanner, List<String> itemList) {
        System.out.print("Enter the item to search: ");
        String item = scanner.nextLine();

        if (itemList.contains(item)) {
            System.out.println("Item found in the list.");
        } else {
            System.out.println("Item not found in the list.");
        }
    }

    /**
     * Deletes an item from the list if it exists.
     */
    private static void deleteItem(Scanner scanner, List<String> itemList) {
        System.out.print("Enter the item to delete: ");
        String item = scanner.nextLine();

        // The remove method returns true if the item was found and removed, false otherwise
        if (itemList.remove(item)) {
            System.out.println("Deleted successfully");
        } else {
            System.out.println("Item does not exist.");
        }
    }

    /**
     * Displays all items currently in the list.
     */
    private static void displayList(List<String> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("The Items in the list are :");
        for (String item : itemList) {
            System.out.println(item);
        }
    }
}