import java.io.Serializable;

public class UserBean implements Serializable {
    private String userID;
    private String password;
    private String name;
    private int incorrectAttempts;
    private int lockStatus; // Number(2) in SQL = int in Java
    private String userType;

    // Constructors
    public UserBean() {}

    public UserBean(String userID, String password, String name, int incorrectAttempts, int lockStatus, String userType) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.incorrectAttempts = incorrectAttempts;
        this.lockStatus = lockStatus;
        this.userType = userType;
    }

    // Getters and Setters (Scenario 8a, 9a, 10a, 11a)

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getIncorrectAttempts() { return incorrectAttempts; }
    public void setIncorrectAttempts(int incorrectAttempts) { this.incorrectAttempts = incorrectAttempts; }

    public int getLockStatus() { return lockStatus; }
    public void setLockStatus(int lockStatus) { this.lockStatus = lockStatus; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    @Override
    public String toString() {
        return "UserBean{" +
               "ID='" + userID + '\'' +
               ", Pwd='" + password + '\'' +
               ", Name='" + name + '\'' +
               ", Attempts=" + incorrectAttempts +
               ", Lock=" + lockStatus +
               ", Type='" + userType + '\'' +
               '}';
    }
}