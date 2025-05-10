import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Manages user authentication including login, sign-up, and account recovery operations.
 */
public class AuthenticationManager 
{

    /** Tracks the number of consecutive failed login attempts. */
    private int loginAttempts = 0;

    /** Stores the timestamp until which login is locked due to repeated failures. */
    private LocalDateTime lockedUntil = null;

    /**
     * Attempts to log in a user by checking the provided credentials.
     *
     * @param email    The user's email address.
     * @param password The user's password.
     * @return true if credentials are correct and login is successful; false otherwise.
     */
    public boolean login(String email, String password) 
    {
        if (lockedUntil != null && LocalDateTime.now().isBefore(lockedUntil)) 
        {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].equals(email) && parts[2].equals(password)) 
                {
                    loginAttempts = 0;
                    return true;
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        loginAttempts++;
        if (loginAttempts >= 3) 
        {
            lockedUntil = LocalDateTime.now().plusMinutes(15);
        }
        return false;
    }

    /**
     * Signs up a new user by adding their information to the database file.
     *
     * @param user The user to be registered.
     * @return true if sign-up is successful; false if email already exists or an error occurs.
     */
    public boolean signUp(User user) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length >= 2 && (parts[1].equalsIgnoreCase(user.getEmail()) || parts[0].equalsIgnoreCase(user.getUsername())) )
                {
                    return false;
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Sends a verification code to the user's email.
     *
     * @param email The email to which the verification code will be sent.
     */
    public String generateOTP() {
    Random random = new Random();
    int otp = 100000 + random.nextInt(900000); // 6-digit OTP
    return String.valueOf(otp);
    }


    /**
     * Verifies a given code for the user's email.
     *
     * @param email The user's email address.
     * @param code  The verification code entered by the user.
     * @return true if the code is valid; false otherwise.
     */
    public boolean verifyCode(String enteredOTP, String storedOTP) 
    {
        if(enteredOTP != null && enteredOTP.equals(storedOTP))
        {
            return true;
        }
        return false;
    }

    
}


