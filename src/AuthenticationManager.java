
import java.io.*;
import java.time.LocalDateTime;

public class AuthenticationManager 
{
    private int loginAttempts = 0;
    private LocalDateTime lockedUntil = null;

    public boolean login(String email, String password) 
    {
        if (lockedUntil != null && LocalDateTime.now().isBefore(lockedUntil)) 
        {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].equals(email) && parts[2].equals(password)) {
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
        if (loginAttempts >= 3) {
            lockedUntil = LocalDateTime.now().plusMinutes(15);
        }
        return false;
    }

    

    public boolean signUp(User user) {
        
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equalsIgnoreCase(user.getEmail())) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public void sendVerificationCode(String email) 
    {
        
    }

    public boolean verifyCode(String email, String code) 
    {
        return true; 
    }

    public void resetPassword(String email, String newPassword) 
    {
        
    }
}

