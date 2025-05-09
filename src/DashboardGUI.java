import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardGUI extends JFrame {
    private String username;

    public DashboardGUI(String username) {
        this.username = username;
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());
        this.setTitle("Dashboard");
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JLabel welcomelabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomelabel.setFont(new Font("Arial", Font.BOLD, 26));
        welcomelabel.setForeground(Color.WHITE);
        welcomelabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel welcomepanel = new JPanel();
        welcomepanel.setBackground(new Color(45, 45, 45));
        welcomepanel.setLayout(new BorderLayout());
        welcomepanel.add(welcomelabel, BorderLayout.CENTER);

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(Color.DARK_GRAY);
        mainpanel.setLayout(new GridLayout(5, 1, 15, 15));
        mainpanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton incomeBtn = styleButton("Track Income");
        JButton expenseBtn = styleButton("Track Expenses");
        JButton budgetBtn = styleButton("Budgeting & Analysis");
        JButton reminderBtn = styleButton("Reminders");
        JButton logoutBtn = logoutButton("Logout");

        mainpanel.add(incomeBtn);
        mainpanel.add(expenseBtn);
        mainpanel.add(budgetBtn);
        mainpanel.add(reminderBtn);
        mainpanel.add(logoutBtn);

        
        incomeBtn.addActionListener(e -> {
            dispose();
            // new IncomeTrackerGUI(username);
        });

        expenseBtn.addActionListener(e -> {
            dispose();
            // new ExpenseTrackerGUI(username);
        });

        budgetBtn.addActionListener(e -> {
            dispose();
            // new BudgetGUI(username);
        });

        reminderBtn.addActionListener(e -> {
            dispose();
            // new ReminderGUI(username);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        this.add(welcomepanel, BorderLayout.NORTH);
        this.add(mainpanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton styleButton(String text) 
    {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); 
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }

    private JButton logoutButton(String text) 
    {
        JButton button = new JButton(text);
        button.setForeground(Color.LIGHT_GRAY);
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }
}



