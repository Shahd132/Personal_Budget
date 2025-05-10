import javax.swing.*;
import java.awt.*;

public class BudgetAppGUI {
    private BudgetManager manager;
    private JFrame frame;
    private JTextField categoryField, amountField, expenseAmountField;
    private JTextArea displayArea;
    private String username; // Add username field

    public BudgetAppGUI(String username) { // Modify constructor to accept username
        this.username = username; // Assign the username to the class field
        manager = new BudgetManager();
        frame = new JFrame("💰 Personal Budgeting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(33, 33, 33));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);

        categoryField = new JTextField();
        categoryField.setPreferredSize(new Dimension(250, 30));

        amountField = new JTextField();
        amountField.setPreferredSize(new Dimension(250, 30));

        expenseAmountField = new JTextField();
        expenseAmountField.setPreferredSize(new Dimension(250, 30));

        displayArea = new JTextArea(12, 50);
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(40, 40, 40));
        displayArea.setForeground(Color.WHITE);
        displayArea.setFont(font);
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addBudgetButton = createStyledButton("➕ Add Budget", font, new Dimension(130, 30));
        JButton addExpenseButton = createStyledButton("💸 Add Expense", font, new Dimension(130, 30));
        JButton analyzeButton = createStyledButton("📊 Analyze", font, new Dimension(200, 40));

        // Create Back to Dashboard button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(102, 0, 153));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(font);
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the current BudgetApp window
            new DashboardGUI(username); // Assuming you have a DashboardGUI class that accepts username
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(33, 33, 33));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        mainPanel.add(createLabeledInputRow("Category:", categoryField, null, labelFont, font));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createLabeledInputRow("Budget Amount:", amountField, addBudgetButton, labelFont, font));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createLabeledInputRow("Expense Amount:", expenseAmountField, addExpenseButton, labelFont, font));
        mainPanel.add(Box.createVerticalStrut(10));

        JPanel analyzePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        analyzePanel.setBackground(new Color(33, 33, 33));
        analyzePanel.add(analyzeButton);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(analyzePanel);

        mainPanel.add(Box.createVerticalStrut(10)); // Adding some space before back button
        mainPanel.add(backButton); // Add the back button to the panel

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addBudgetButton.addActionListener(e -> {
            String category = categoryField.getText();
            try {
                double amount = Double.parseDouble(amountField.getText());
                Budget budget = new Budget(category, amount);
                manager.addBudget(budget);
                displayArea.append("✅ Budget for " + category + " added: $" + amount + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("❌ Invalid budget amount.\n");
            } catch (IllegalArgumentException ex) {
                displayArea.append("❌ " + ex.getMessage() + "\n");
            }
        });

        addExpenseButton.addActionListener(e -> {
            String category = categoryField.getText();
            try {
                double expense = Double.parseDouble(expenseAmountField.getText());
                Expense exp = new Expense(category, expense);
                manager.addExpense(exp);
                displayArea.append("💰 Expense for " + category + " added: $" + expense + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("❌ Invalid expense amount.\n");
            } catch (IllegalArgumentException ex) {
                displayArea.append("❌ " + ex.getMessage() + "\n");
            }
        });

        analyzeButton.addActionListener(e -> {
            String category = categoryField.getText().trim();
            if (category.isEmpty()) {
                displayArea.append("⚠️ Please enter a category to analyze.\n");
                return;
            }

            String result = manager.analyzeSpendingForCategory(category);
            displayArea.append("\n=========================\n");
            displayArea.append(result + "\n");
            displayArea.append("=========================\n\n");
        });

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(new Color(102, 0, 153));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setMargin(new Insets(4, 10, 4, 10));
        button.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 153), 1, true));
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }

    private JPanel createLabeledInputRow(String labelText, JTextField field, JButton button, Font labelFont, Font font) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 33, 33));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);

        panel.add(label);
        panel.add(field);
        if (button != null) panel.add(button);

        return panel;
    }

    public static void main(String[] args) {
        new BudgetAppGUI("UserName");
    }
}

