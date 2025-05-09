import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RemainderManager {
    private static ArrayList<Remainder> remainderList = new ArrayList<>();

    // Method to save reminder with category
    public static void saveRemainder(String title, String date, String time, String category) {
        try {
            // Combine date and time into a single string for parsing
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateTime = date + " " + time;
            Date reminderDate = dateTimeFormat.parse(dateTime);

            Date currentDate = new Date();
            if (reminderDate.before(currentDate)) {
                JOptionPane.showMessageDialog(null, "Reminder date must be in the future.");
                return;
            }

            Remainder remainder = new Remainder(title, reminderDate, time, category);
            remainderList.add(remainder);

            // Calculate the delay until the reminder
            long delay = remainder.getReminderDate().getTime() - currentDate.getTime();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendNotification(remainder);
                }
            }, delay);

            JOptionPane.showMessageDialog(null, "Reminder saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date or time format.");
        }
    }

    // Method to send notification
    private static void sendNotification(Remainder remainder) {
        JOptionPane.showMessageDialog(null, "Reminder: " + remainder.getTitle() + " at " + remainder.getReminderTime());
    }

    // Method to remove a remainder by index
    public static void removeRemainder(int index) {
        if (index >= 0 && index < remainderList.size()) {
            remainderList.remove(index);
            JOptionPane.showMessageDialog(null, "Reminder removed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Reminder not found.");
        }
    }

    // Get all reminders
    public static ArrayList<Remainder> getAllReminders() {
        return remainderList;
    }
}
