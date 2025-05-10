import javax.swing.*;
import java.awt.*;

public class BudgetAppGUI {
    private BudgetManager manager;
    private JFrame frame;
    private JTextField categoryField, amountField, expenseAmountField;
    private JTextArea displayArea;
    private String username; 

    public BudgetAppGUI(String username) {
        this.username = username; 
        manager = new BudgetManager();
        frame = new JFrame(" Personal Budgeting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700); 
        frame.setLocationRelativeTo(null);  
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

        JButton addBudgetButton = createStyledButton(" Add Budget", font, new Dimension(130, 30));
        JButton addExpenseButton = createStyledButton("Add Expense", font, new Dimension(130, 30));
        JButton analyzeButton = createStyledButton(" Analyze", font, new Dimension(200, 40));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(70, 130, 180));  
        backButton.setForeground(Color.WHITE); 
        backButton.setFont(font);
        backButton.setPreferredSize(new Dimension(200, 40));
        backButton.addActionListener(e -> {
            frame.dispose(); 
            new DashboardGUI(username); 
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        buttonPanel.setBackground(new Color(33, 33, 33));
        buttonPanel.add(analyzeButton);
        buttonPanel.add(backButton);  

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);  

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
                displayArea.append("Budget for " + category + " added: $" + amount + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append(" Invalid budget amount.\n");
            } catch (IllegalArgumentException ex) {
                displayArea.append(" " + ex.getMessage() + "\n");
            }
        });

        addExpenseButton.addActionListener(e -> {
            String category = categoryField.getText();
            try {
                double expense = Double.parseDouble(expenseAmountField.getText());
                Expense exp = new Expense(category, expense);
                manager.addExpense(exp);
                displayArea.append(" Expense for " + category + " added: $" + expense + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("Invalid expense amount.\n");
            } catch (IllegalArgumentException ex) {
                displayArea.append(  ex.getMessage() + "\n");
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
        button.setBackground(new Color(70, 130, 180));  
        button.setForeground(Color.WHITE);  
        button.setFocusPainted(false);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setMargin(new Insets(4, 10, 4, 10));
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1, true));  
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        return button;
    }

    private JPanel createLabeledInputRow(String labelText, JTextField field, JButton button, Font labelFont, Font font) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 33, 33));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(0, 0, 10, 0);  
        gbcLabel.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.WHITE);

        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.gridx = 0;
        gbcField.gridy = 1;
        gbcField.insets = new Insets(0, 0, 10, 0);  
        gbcField.fill = GridBagConstraints.HORIZONTAL;

        field.setPreferredSize(new Dimension(250, 30));

        panel.add(label, gbcLabel);
        panel.add(field, gbcField);

        if (button != null) {
            GridBagConstraints gbcButton = new GridBagConstraints();
            gbcButton.gridx = 1;
            gbcButton.gridy = 1;
            gbcButton.insets = new Insets(0, 10, 0, 0);  
            panel.add(button, gbcButton);
        }

        return panel;
    }

    public static void main(String[] args) {
        new BudgetAppGUI("UserName");
    }
}
