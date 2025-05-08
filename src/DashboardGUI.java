
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardGUI extends JFrame
{
    private String currentUserEmail;  // Optional: to keep track of logged-in user

    public DashboardGUI(String email) {
        this.currentUserEmail = email;

        setTitle("Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + currentUserEmail, SwingConstants.CENTER);
        panel.add(welcomeLabel);

        JButton incomeBtn = new JButton("Track Income");
        JButton expenseBtn = new JButton("Track Expenses");
        JButton budgetBtn = new JButton("Budgeting & Analysis");
        JButton reminderBtn = new JButton("Reminders");
        JButton logoutBtn = new JButton("Logout");

        panel.add(incomeBtn);
        panel.add(expenseBtn);
        panel.add(budgetBtn);
        panel.add(reminderBtn);
        panel.add(logoutBtn);

        add(panel);

        // 👇 Handle button clicks to navigate
        incomeBtn.addActionListener(e -> {
            dispose(); // Close Dashboard
            // new IncomeTrackerGUI(currentUserEmail); // Open Income GUI
        });

        expenseBtn.addActionListener(e -> {
            dispose();
            // new ExpenseTrackerGUI(currentUserEmail);
        });

        budgetBtn.addActionListener(e -> {
            dispose();
            // new BudgetGUI(currentUserEmail);
        });

        reminderBtn.addActionListener(e -> {
            dispose();
            // new ReminderGUI(currentUserEmail);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            // new LoginGUI(); // Go back to login
        });

        setVisible(true);
    }

    // Main for testing
    public static void main(String[] args) {
        new DashboardGUI("user@example.com");
    }
}


