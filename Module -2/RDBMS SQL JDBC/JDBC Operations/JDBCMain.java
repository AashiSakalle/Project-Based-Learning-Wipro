import java.util.Arrays;
import java.util.List;

public class JDBCMain {

    public static void main(String[] args) {
        DBOperations dbOps = new DBOperations();

        // --- Sample Data (Provided in the image for the table) ---
        // UserID: AB1001, UserType: Admin
        // UserID: TA1002, UserType: Employee
        // UserID: RS1003, UserType: Employee
        
        System.out.println("--- Scenario 1: Get User Type (1c) ---");
        String userType1 = dbOps.getUserType("AB1001");
        System.out.println("UserType for AB1001: " + userType1);
        System.out.println("UserType for TA1002: " + dbOps.getUserType("TA1002"));
        
        System.out.println("\n--- Scenario 2: Get Incorrect Attempts (2c) ---");
        String attempts2 = dbOps.getIncorrectAttempts("TA1002");
        System.out.println("Incorrect Attempts for TA1002: " + attempts2); // Should be 'No Incorrect Attempt' based on sample data
        
        System.out.println("\n--- Scenario 3: Change User Type (3c) ---");
        String updateResult3 = dbOps.changeUserType("TA1002");
        System.out.println("Change UserType for TA1002 result: " + updateResult3);
        
        System.out.println("\n--- Scenario 4: Get Lock Status Count (4c) ---");
        int lockStatusCount4 = dbOps.getLockStatus();
        System.out.println("Count of users with LockStatus 0: " + lockStatusCount4); // Should be 3 based on sample data
        
        System.out.println("\n--- Scenario 5: Change Name (5c) ---");
        String nameChangeResult5 = dbOps.changeName("AB1001", "Justin");
        System.out.println("Name change for AB1001 result: " + nameChangeResult5);
        
        System.out.println("\n--- Scenario 6: Change Admin Password (6c) ---");
        String passwordChangeResult6 = dbOps.changePassword("newPwd");
        System.out.println("Password change result for Admins: " + passwordChangeResult6);
        
        System.out.println("\n--- Scenario 7: Add User 1 (7d) ---");
        UserBean newUser7 = new UserBean("PK1004", "p456", "Priya", 0, 0, "Employee");
        String insertResult7 = dbOps.addUser_1(newUser7);
        System.out.println("Insert User 1 result: " + insertResult7);

        System.out.println("\n--- Scenario 8: Add User 2 (LockStatus check) (8d) ---");
        UserBean newUser8 = new UserBean("KR1005", "k789", "Karthik", 0, 1, "Employee"); // LockStatus 1
        String insertResult8 = dbOps.addUser_2(newUser8);
        System.out.println("Insert User 2 (LockStatus 1) result: " + insertResult8);
        
        System.out.println("\n--- Scenario 9: Get Users by UserType (9d) ---");
        List<UserBean> adminUsers9 = dbOps.getUsers("Admin");
        System.out.println("Users with type 'Admin':");
        adminUsers9.forEach(System.out::println);
        
        System.out.println("\n--- Scenario 10: Store All Records (10d) ---");
        List<UserBean> allUsers10 = dbOps.storeAllRecords();
        System.out.println("All Records:");
        allUsers10.forEach(System.out::println);
        
        System.out.println("\n--- Scenario 11: Get All Names (11d) ---");
        String[] allNames11 = dbOps.getNames();
        System.out.println("All Names: " + Arrays.toString(allNames11));
    }
}