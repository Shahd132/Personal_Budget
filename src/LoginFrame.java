
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends JFrame 
{
    private AuthenticationManager authManager = new AuthenticationManager();
    public LoginFrame() 
    {
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());
        this.setTitle("Log In");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Welcome to Personal Budget!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title, BorderLayout.CENTER);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 60, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;


        JLabel emailLabel = new JLabel("Email");
        styleLabel(emailLabel);
        mainPanel.add(emailLabel, gbc);

        gbc.gridy++;
        JTextField emailField = new JTextField();
        styleInputField(emailField);
        mainPanel.add(emailField, gbc);


        gbc.gridy++;
        JLabel passLabel = new JLabel("Password");
        styleLabel(passLabel);
        mainPanel.add(passLabel, gbc);


        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        styleInputField(passwordField);
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginBtn = new JButton("Log In");
        styleButton(loginBtn);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.DARK_GRAY);
        loginPanel.add(loginBtn);
        mainPanel.add(loginPanel, gbc);


        
        gbc.gridy++;
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBackground(Color.DARK_GRAY);
        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setForeground(Color.WHITE);
        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBackground(Color.DARK_GRAY);
        signUpBtn.setBorderPainted(false);
        signUpBtn.setFocusPainted(false);
        signUpBtn.setContentAreaFilled(false);
        signUpBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpPanel.add(noAccountLabel);
        signUpPanel.add(signUpBtn);
        mainPanel.add(signUpPanel, gbc);

        
        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
        
            if (authManager.login(email, password)) 
            {
               
                
                try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) 
                {
                     String line;
                     while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 3 && parts[1].equals(email) && parts[2].equals(password)) {
                             JOptionPane.showMessageDialog(this, "Login successful!");
                             dispose();
                             new DashboardGUI(parts[0]);
                             break;
                        }   
                     }
                }
                catch(IOException c)
                {
                    c.printStackTrace();
                } 
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Invalid credentials or account locked.");
            }
        });
        

        signUpBtn.addActionListener(e -> {
            dispose();
            new SignUpFrame();
        });

        
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleLabel(JLabel label) 
    {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
    }

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

    private void styleButton(JButton button) 
    {
        Dimension buttonSize = new Dimension(100, 40);
        button.setPreferredSize(buttonSize);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    
}





