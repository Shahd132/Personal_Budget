
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SignUpFrame extends JFrame {
    private AuthenticationManager authManager = new AuthenticationManager();
    private int userId=0;

    public SignUpFrame() 
    {
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());
        setTitle("Sign Up");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.add(title, BorderLayout.CENTER);

       
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 60, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        
        JLabel usernameLabel = new JLabel("Username");
        styleLabel(usernameLabel);
        mainPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        JTextField usernameField = new JTextField();
        styleInputField(usernameField);
        mainPanel.add(usernameField, gbc);

        
        gbc.gridy++;
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
        JButton signUpBtn = new JButton("Create Account");
        styleButton(signUpBtn);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.DARK_GRAY);
        btnPanel.add(signUpBtn);
        mainPanel.add(btnPanel, gbc);

        gbc.gridy++;
        JButton backBtn = new JButton("Back");
        styleButton(backBtn);
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.DARK_GRAY);
        backPanel.add(backBtn);
        mainPanel.add(backPanel, gbc);

        
        signUpBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) 
            {
                writer.write(username + "," + email + "," + password);
                writer.newLine();
                userId++;
                User user = new User(userId,username, email, password);
                if (authManager.signUp(user)) 
                {
                    JOptionPane.showMessageDialog(this, "Account created!");
                    dispose();
                    new LoginFrame();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "This email already used");
                    
                }
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving user.");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
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
        Dimension buttonSize = new Dimension(180, 40);
        button.setPreferredSize(buttonSize);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}

