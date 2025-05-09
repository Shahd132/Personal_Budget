import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemainderAppGUI {
    private JFrame frame;
    private JTable remainderTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, dateField, timeField, categoryField;
    private String username;

    public RemainderAppGUI(String username) {
        this.username = username;
        frame = new JFrame("Reminder App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // Input Panel for adding reminders
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.setBackground(Color.DARK_GRAY);

        // Labels and text fields
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.WHITE);
        inputPanel.add(titleLabel);
        titleField = new JTextField();
        inputPanel.add(titleField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setForeground(Color.WHITE);
        inputPanel.add(dateLabel);
        dateField = new JTextField();
        inputPanel.add(dateField);

        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setForeground(Color.WHITE);
        inputPanel.add(timeLabel);
        timeField = new JTextField();
        inputPanel.add(timeField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setForeground(Color.WHITE);
        inputPanel.add(categoryLabel);
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        // Save button
        JButton saveButton = new JButton("Save Reminder");
        saveButton.setBackground(new Color(128, 0, 128)); // Purple button color
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                String category = categoryField.getText();

                if (title.isEmpty() || date.isEmpty() || time.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                } else {
                    RemainderManager.saveRemainder(title, date, time, category);
                    refreshTable();
                }
            }
        });
        inputPanel.add(saveButton);

        // Table to display reminders
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Title");
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Category");

        remainderTable = new JTable(tableModel);
        remainderTable.setBackground(new Color(245, 245, 245));
        remainderTable.setSelectionBackground(new Color(173, 216, 230));

        JScrollPane scrollPane = new JScrollPane(remainderTable);

        // Delete button
        JButton deleteButton = new JButton("Delete Reminder");
        deleteButton.setBackground(new Color(128, 0, 128)); // Purple button color
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = remainderTable.getSelectedRow();
                if (selectedRow != -1) {
                    RemainderManager.removeRemainder(selectedRow);
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a reminder to delete.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);

        // Back button to go back to Dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(128, 0, 128)); // Purple button color
        backButton.setForeground(Color.WHITE);
       backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new DashboardGUI(username);
    }
});


        buttonPanel.add(backButton);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Refresh table with all reminders
    private void refreshTable() {
        ArrayList<Remainder> reminders = RemainderManager.getAllReminders();
        tableModel.setRowCount(0);

        for (Remainder remainder : reminders) {
            tableModel.addRow(new Object[] {
                remainder.getTitle(),
                remainder.getReminderDate().toString(),
                remainder.getReminderTime().toString(),
                remainder.getCategory()
            });
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RemainderAppGUI gui = new RemainderAppGUI("UserName");
                gui.display();
            }
        });
    }
}
