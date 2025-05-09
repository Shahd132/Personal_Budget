import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ShowExpenseGUI extends JFrame {

    public ShowExpenseGUI(String username) {
        setTitle("Expense History");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        try (BufferedReader br = new BufferedReader(new FileReader("expenses_" + username + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            textArea.setText("No expense history found.");
        }

        add(new JLabel("Your Expense History:", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
