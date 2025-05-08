public class User 
{
    private int userId;
    private String username;
    private String email;
    private String password;
    private boolean isVerified;
    private UserInfo userInfo;

    public User(int userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isVerified = false;
    }

    public UserInfo getProfile() {
        return userInfo;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void updateProfileInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isAuthenticated(String passwordInput) {
        return password.equals(passwordInput); 
    }
}

