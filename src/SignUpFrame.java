import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The {@code SignUpFrame} class represents the GUI window for user registration.
 * It allows users to enter their username, email, and password and sign up for a new account.
 * User data is saved to a local file and validated using an {@link AuthenticationManager}.
 */
public class SignUpFrame extends JFrame 
{
    private AuthenticationManager authManager = new AuthenticationManager();

    /** Static user ID counter to ensure uniqueness across sessions. */
    private static int userId = 0;

    /**
     * Constructs the SignUpFrame window and initializes its components.
     * Sets up the layout, styling, and action listeners for the sign-up form.
     */
    public SignUpFrame() 
    {
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());
        setTitle("Sign Up");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title panel setup
        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title, BorderLayout.CENTER);

        // Main form panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(200, 100, 150, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;


        // Username field
        JLabel usernameLabel = new JLabel("Username");
        styleLabel(usernameLabel);
        mainPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        JTextField usernameField = new JTextField();
        styleInputField(usernameField);
        mainPanel.add(usernameField, gbc);

        // Email field
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email");
        styleLabel(emailLabel);
        mainPanel.add(emailLabel, gbc);

        gbc.gridy++;
        JTextField emailField = new JTextField();
        styleInputField(emailField);
        mainPanel.add(emailField, gbc);

        gbc.gridy++;
        JLabel phonelabel = new JLabel("Phone Number");
        styleLabel(phonelabel);
        mainPanel.add(phonelabel, gbc);

        gbc.gridy++;
        JTextField phoneField = new JTextField();
        styleInputField(phoneField);
        mainPanel.add(phoneField, gbc);

        // Password field
        gbc.gridy++;
        JLabel passLabel = new JLabel("Password");
        styleLabel(passLabel);
        mainPanel.add(passLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        styleInputField(passwordField);
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        JLabel passconfLabel = new JLabel("Confirm Password");
        styleLabel(passconfLabel);
        mainPanel.add(passconfLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordconfirmField = new JPasswordField();
        styleInputField(passwordconfirmField);
        mainPanel.add(passwordconfirmField, gbc);

        // Sign Up button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton signUpBtn = new JButton("Create Account");
        styleButton(signUpBtn);
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.DARK_GRAY);
        btnPanel.add(signUpBtn);
        mainPanel.add(btnPanel, gbc);

        // Back button
        gbc.gridy++;
        JButton backBtn = new JButton("Back");
        styleButton(backBtn);
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.DARK_GRAY);
        backPanel.add(backBtn);
        mainPanel.add(backPanel, gbc);

        /**
         * Action listener for the sign-up button.
         * Validates user input, writes data to file, and signs up the user.
         */
        signUpBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirnpassword = new String(passwordconfirmField.getPassword());
            String phone=phoneField.getText().trim();
            


            if (username.isEmpty() || email.isEmpty() || password.isEmpty() ||phone.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            if (username.length() < 3 ||username.length()>50) {
                JOptionPane.showMessageDialog(this, "username must be at least 3 characters and at most 50 characters and at least one uppercase letter, one lowercase letter, one number, and one special character.");
                return;
            }
            if (!isValidEmail(email)) 
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
                return;
            }
            //check confirmation password
            if(!password.equals(confirnpassword))
            {
                JOptionPane.showMessageDialog(this, "please confirm the password correctly");
                return;          
            }
            if (isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters and at most 16 characters");
                return;
            }
            // Validate phone number
            if(isdigits(phone))
            {
                JOptionPane.showMessageDialog(this, "Phone number must contain only digits.");
                return;
                        
            }
            if (!isValidPhoneNumber(phone)) 
            {
                JOptionPane.showMessageDialog(this, "Phone number must be exactly 16 digits.");
                return;
            }
                   
                  
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) 
            {
                userId++;
                UserInfo info=new UserInfo(phone);
                info.setPhoneNumber(phone);
                User user = new User(userId, username, email, password);
                 
                   
                if (authManager.signUp(user)) 
                {
                   String otp = authManager.generateOTP();  
                    JOptionPane.showMessageDialog(this, "Your OTP code is: " + otp);

                    String storedOTP = otp;  
                    String enteredOTP = JOptionPane.showInputDialog(this, "Enter the OTP sent to your phone:");
                    if (authManager.verifyCode(enteredOTP,storedOTP)) 
                    {
                        JOptionPane.showMessageDialog(this, "OTP verified! Account created successfully.");
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.");
                        return;
                    }
                    try (BufferedWriter write = new BufferedWriter(new FileWriter("users.txt", true))) 
                    {
                        write.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
                        write.newLine();
                    } 
                    catch (IOException c) 
                    {
                        c.printStackTrace();
                    }
                    dispose();
                    new LoginFrame();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "This email or username already used");
                    return;
                }
            } 
            catch (IOException ex) 
            {
                JOptionPane.showMessageDialog(this, "Error saving user.");
            }
            
        });

        /**
         * Action listener for the back button.
         * Returns to the login window.
         */
        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        // Add panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Styles a JLabel with font and color settings.
     *
     * @param label the JLabel to be styled
     */
    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

    /**
     * Styles a JTextField with padding, background color, and font.
     *
     * @param field the JTextField or JPasswordField to be styled
     */
    private void styleInputField(JTextField field) 
    {
        Dimension fieldSize = new Dimension(500, 40);
        field.setMaximumSize(fieldSize);
        field.setPreferredSize(fieldSize);
        field.setMinimumSize(fieldSize);
        field.setBackground(new Color(230, 230, 230));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * Styles a JButton with padding, colors, and font settings.
     *
     * @param button the JButton to be styled
     */
    private void styleButton(JButton button) 
    {
        Dimension buttonSize = new Dimension(180, 40);
        button.setPreferredSize(buttonSize);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

   
    public boolean isValidPhoneNumber(String phone) {
    return phone.matches("^\\+\\d{1,3}\\d{6,14}$") &&phone.matches("^\\d{16}$");
    }


    public boolean isdigits(String phone)
    {
        for(char digit:phone.toCharArray())
        {
             if (!Character.isDigit(digit)) 
            {
              return true;  
            }
        }
        return false;
    }

    public boolean isValidEmail(String email) 
    {
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return email.matches(emailRegex);
    }

    public boolean isValidPassword(String inputPassword) {
    if (inputPassword.length() < 8 || inputPassword.length() > 16) {
        return false; // Length check
    }

    // Regex checks:
    String upperCasePattern = ".*[A-Z].*";
    String lowerCasePattern = ".*[a-z].*";
    String digitPattern = ".*\\d.*";
    String specialCharPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*";

    if (!inputPassword.matches(upperCasePattern)) return false;
    if (!inputPassword.matches(lowerCasePattern)) return false;
    if (!inputPassword.matches(digitPattern)) return false;
    if (!inputPassword.matches(specialCharPattern)) return false;

    // Match stored password
    return true;
    }



}



