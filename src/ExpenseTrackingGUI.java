import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ExpenseTrackingGUI extends JFrame {
    private String username;

    public ExpenseTrackingGUI(String username) {
        this.username = username;
        setTitle("Track Expenses");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Add Expense", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(45, 45, 45));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 15, 15));
        formPanel.setBackground(Color.DARK_GRAY);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JTextField amountField = new JTextField(20);
        JTextField categoryField = new JTextField(20);
        JTextField dateField = new JTextField(20);

        JButton saveBtn = styleButton("Save Expense");
        JButton viewBtn = styleButton("View Expense History");
        JButton backBtn = logoutButton("Back");

        formPanel.add(new JLabel("Amount:"));
        formPanel.add(amountField);
        formPanel.add(new JLabel("Category:"));
        formPanel.add(categoryField);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateField);

        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        savePanel.setBackground(Color.DARK_GRAY);
        savePanel.add(saveBtn);

        JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewPanel.setBackground(Color.DARK_GRAY);
        viewPanel.add(viewBtn);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.setBackground(Color.DARK_GRAY);
        backPanel.add(backBtn);

        formPanel.add(savePanel);
        formPanel.add(viewPanel);
        formPanel.add(backPanel);

        saveBtn.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            String category = categoryField.getText().trim();
            String date = dateField.getText().trim();

            if (amountText.isEmpty() || category.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);
                Expense expense = new Expense("Manual", amount, date, category);  // ✅ تعديل هنا
                saveExpenseToFile(expense);  // ✅ والتعديل هنا
                JOptionPane.showMessageDialog(this, "Expense saved successfully.");
                amountField.setText("");
                categoryField.setText("");
                dateField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number.");
            }
        });

        viewBtn.addActionListener(e -> new ShowExpenseGUI(username));

        backBtn.addActionListener(e -> {
            dispose();
            new DashboardGUI(username);
        });

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void saveExpenseToFile(Expense expense) {
        try (FileWriter fw = new FileWriter("expenses_" + username + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(expense.toFileString());  // ✅ تعديل هنا
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving expense.");
        }
    }

    private JButton styleButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(220, 20, 60));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    private JButton logoutButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.LIGHT_GRAY);
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(90, 25));
        return button;
    }
}
