import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {

    // --- Scenario 1: Retrieve UserType by ID --- (1a, 1b)
    /**
     * Retrieves the UserType for a given userID.
     */
    public String getUserType(String userID) {
        String userType = "User Not Found";
        String sql = "SELECT UserType FROM User WHERE UserID = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userType = rs.getString("UserType");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            userType = "Database Error";
        }
        return userType;
    }

    // --- Scenario 2: Retrieve IncorrectAttempts by ID --- (2a, 2b)
    /**
     * Retrieves IncorrectAttempts and returns a specific message based on the value.
     */
    public String getIncorrectAttempts(String userID) {
        String result = "User Not Found";
        String sql = "SELECT IncorrectAttempts FROM User WHERE UserID = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int attempts = rs.getInt("IncorrectAttempts");
                    if (attempts == 0) {
                        result = "No Incorrect Attempt";
                    } else if (attempts == 1) {
                        result = "One Time";
                    } else {
                        result = "Incorrect Attempt Exceeded";
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result = "Database Error";
        }
        return result;
    }

    // --- Scenario 3: Change UserType to Admin --- (3b)
    /**
     * Updates the UserType to "Admin" for a given userID.
     */
    public String changeUserType(String userID) {
        String sql = "UPDATE User SET UserType = 'Admin' WHERE UserID = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userID);
            int rowsUpdated = ps.executeUpdate();

            // Return 'Update Success' if one row updated, else 'Update Failed'
            if (rowsUpdated == 1) {
                return "Update Success";
            } else {
                return "Update Failed";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Update Failed (Database Error)";
        }
    }

    // --- Scenario 4: Count users with LockStatus 0 --- (4b)
    /**
     * Counts the total rows where LockStatus is 0.
     */
    public int getLockStatus() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM User WHERE LockStatus = 0";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // --- Scenario 5: Change Name by UserID --- (5b)
    /**
     * Changes the Name for a given userID.
     */
    public String changeName(String id, String newName) {
        String sql = "UPDATE User SET Name = ? WHERE UserID = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setString(2, id);
            int rowsUpdated = ps.executeUpdate();

            // Return 'Success' or 'Failed'
            return rowsUpdated > 0 ? "Success" : "Failed";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed (Database Error)";
        }
    }

    // --- Scenario 6: Change Password for records with UserType 'Admin' --- (6b)
    /**
     * Changes the Password for all records with UserType 'Admin'.
     */
    public String changePassword(String newPassword) {
        String sql = "UPDATE User SET Password = ? WHERE UserType = 'Admin'";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            int rowsUpdated = ps.executeUpdate();

            // Return 'Changed' or 'Failure'
            return rowsUpdated > 0 ? "Changed" : "Failure";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Failure (Database Error)";
        }
    }

    // --- Scenario 7: Insert a new record (add_User_1) --- (7c)
    /**
     * Inserts a new UserBean record into the table.
     */
    public String addUser_1(UserBean user) {
        String sql = "INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUserID());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getIncorrectAttempts());
            ps.setInt(5, user.getLockStatus());
            ps.setString(6, user.getUserType());

            int rowsInserted = ps.executeUpdate();

            // Return 'Success' or 'Fail'
            return rowsInserted > 0 ? "Success" : "Fail";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Fail (Database Error)";
        }
    }
    
    // --- Scenario 8: Insert a new record only if lockstatus is 0 (add_User_2) --- (8c)
    /**
     * Inserts a new UserBean record only if its lockstatus is 0.
     */
    public String addUser_2(UserBean user) {
        // Use the method to get the values to insert
        if (user.getLockStatus() != 0) {
            return "Fail (LockStatus is not 0)";
        }
        
        String sql = "INSERT INTO User (UserID, Password, Name, IncorrectAttempts, LockStatus, UserType) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUserID());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getIncorrectAttempts());
            ps.setInt(5, user.getLockStatus());
            ps.setString(6, user.getUserType());

            int rowsInserted = ps.executeUpdate();

            // Return 'Success' or 'Fail'
            return rowsInserted > 0 ? "Success" : "Fail";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Fail (Database Error)";
        }
    }

    // --- Scenario 9: Get Users by UserType --- (9c)
    /**
     * Retrieves all UserBean records for a specified user type and stores them in an ArrayList.
     */
    public ArrayList<UserBean> getUsers(String userType) {
        ArrayList<UserBean> userList = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE UserType = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userType);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserBean user = new UserBean();
                    user.setUserID(rs.getString("UserID"));
                    user.setPassword(rs.getString("Password"));
                    user.setName(rs.getString("Name"));
                    user.setIncorrectAttempts(rs.getInt("IncorrectAttempts"));
                    user.setLockStatus(rs.getInt("LockStatus"));
                    user.setUserType(rs.getString("UserType"));
                    userList.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // --- Scenario 10: Store All Records --- (10c)
    /**
     * Retrieves all UserBean records from the table and stores them in an ArrayList.
     */
    public ArrayList<UserBean> storeAllRecords() {
        ArrayList<UserBean> userList = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserID(rs.getString("UserID"));
                user.setPassword(rs.getString("Password"));
                user.setName(rs.getString("Name"));
                user.setIncorrectAttempts(rs.getInt("IncorrectAttempts"));
                user.setLockStatus(rs.getInt("LockStatus"));
                user.setUserType(rs.getString("UserType"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // --- Scenario 11: Get All Names --- (11c)
    /**
     * Retrieves all names from the table and stores them in a String array.
     */
    public String[] getNames() {
        // Use a List for dynamic sizing, then convert to Array
        List<String> namesList = new ArrayList<>();
        String sql = "SELECT Name FROM User";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                namesList.add(rs.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Convert the List to a String array
        return namesList.toArray(new String[0]);
    }
}