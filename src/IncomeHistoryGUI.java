import javax.swing.*;
import java.awt.*;
import java.io.*;

public class IncomeHistoryGUI extends JFrame {
    private String username;

    public IncomeHistoryGUI(String username) {
        this.username = username;
        setTitle("Income History");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("src/budgeting.png");
        this.setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Income History for " + username, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(45, 45, 45));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyArea.setBackground(Color.DARK_GRAY);
        historyArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        loadIncomeData(historyArea);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.LIGHT_GRAY);
        backBtn.setBackground(Color.DARK_GRAY);
        backBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> {
            dispose();
            new DashboardGUI(username);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(backBtn);

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadIncomeData(JTextArea area) {
        File file = new File("income_" + username + ".txt");
        if (!file.exists()) {
            area.setText("No income records found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder allIncome = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                allIncome.append(line).append("\n");
            }
            area.setText(allIncome.toString());
        } catch (IOException e) {
            area.setText("Error loading income data.");
        }
    }
}
