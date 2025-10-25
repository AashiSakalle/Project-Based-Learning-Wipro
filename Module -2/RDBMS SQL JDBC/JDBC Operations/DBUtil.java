import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // JDBC URL, user, and password for a placeholder MySQL database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    /**
     * Obtains the Connection Object (as required by every scenario)
     */
    public static Connection getConnection() throws SQLException {
        // Load the driver (optional for modern JDBC drivers, but good practice)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            throw new SQLException("JDBC Driver not found.", e);
        }
        
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}