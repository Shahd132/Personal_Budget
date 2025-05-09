import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class IncomeTrackingGUI extends JFrame {
    private String username;

    public IncomeTrackingGUI(String username) {
        this.username = username;
        setTitle("Track Income");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Add Income", SwingConstants.CENTER);
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

        JTextField sourceField = new JTextField(20);
        JTextField amountField = new JTextField(20);
        JTextField dateField = new JTextField(20);  
        sourceField.setPreferredSize(new Dimension(100, 10));
        amountField.setPreferredSize(new Dimension(100, 10));
        dateField.setPreferredSize(new Dimension(100, 10));

        JButton saveBtn = styleButton("Save Income");
        JButton viewBtn = styleButton("View Income History");
        JButton backBtn = logoutButton("Back");

        
        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        savePanel.setBackground(Color.DARK_GRAY);
        savePanel.add(saveBtn);

        JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewPanel.setBackground(Color.DARK_GRAY);
        viewPanel.add(viewBtn);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.setBackground(Color.DARK_GRAY);
        backPanel.add(backBtn);

        
        formPanel.add(new JLabel("Source:"));
        formPanel.add(sourceField);

        formPanel.add(new JLabel("Amount:"));
        formPanel.add(amountField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateField);

        formPanel.add(savePanel);
        formPanel.add(viewPanel);
        formPanel.add(backPanel);

        saveBtn.addActionListener(e -> {
            String source = sourceField.getText().trim();
            String amount = amountField.getText().trim();
            String date = dateField.getText().trim();

            if (source.isEmpty() || amount.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {
                double parsedAmount = Double.parseDouble(amount);
                saveIncomeToFile(source, parsedAmount, date);
                JOptionPane.showMessageDialog(this, "Income saved successfully.");
                sourceField.setText("");
                amountField.setText("");
                dateField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number.");
            }
        });

        viewBtn.addActionListener(e -> {
            new IncomeHistoryGUI(username);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new DashboardGUI(username);
        });

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void saveIncomeToFile(String source, double amount, String date) {
        String line = String.format("Source: %s | Amount: %.2f | Date: %s", source, amount, date);
        try (FileWriter fw = new FileWriter("income_" + username + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving income.");
        }
    }

    private JButton styleButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(130, 30));
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
