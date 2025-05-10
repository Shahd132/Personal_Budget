/**
 * Represents a user in the personal budgeting system.
 * Stores basic user information and supports authentication and profile updates.
 */
public class User 
{
    /** Unique ID for the user. */
    private int userId;

    /** Username of the user. */
    private String username;

    /** Email address of the user. */
    private String email;

    /** Password for the user account. */
    private String password;

    /** Indicates whether the user's email has been verified. */
    private boolean isVerified;

    /** Additional profile information for the user. */
    private UserInfo userInfo;

    /**
     * Constructs a new User with the specified details.
     *
     * @param userId   Unique identifier for the user
     * @param username Username of the user
     * @param email    Email address of the user
     * @param password Password for the account
     */
    public User(int userId, String username, String email, String password) 
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isVerified = false;
    }
    /**
     * Returns the profile information of the user.
     *
     * @return The UserInfo object containing user details
     */
    public UserInfo getProfile() 
    {
        return userInfo;
    }
    /**
     * Returns the user's password.
     *
     * @return The password string
     */
    public String getPassword() 
    {
        return password;
    }
    /**
     * Returns the user's email address.
     *
     * @return The email string
     */
    public String getEmail() 
    {
        return email;
    }
    /**
     * Returns the username.
     *
     * @return The username string
     */
    public String getUsername() 
    {
        return username;
    }
    /**
     * Updates the profile information of the user.
     *
     * @param userInfo A UserInfo object containing new profile data
     */
    public void updateProfileInfo(UserInfo userInfo) 
    {
        this.userInfo = userInfo;
    }
    /**
     * Returns the user's ID as a string.
     *
     * @return The user ID string
     */
    public int getUserId() 
    {
        return userId;
    }
    /**
     * Authenticates the user by comparing the input password to the stored one.
     *
     * @param passwordInput The password input to check
     * @return True if passwords match, otherwise false
     */
    public boolean isAuthenticated(String passwordInput) 
    {
        return password.equals(passwordInput);
    }
}


