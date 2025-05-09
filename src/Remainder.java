import java.util.Date;

public class Remainder {
    private String title;
    private Date reminderDate;
    private String reminderTime;  // تعديل نوع المتغير ليكون String بدلاً من Time
    private String category;

    public Remainder(String title, Date reminderDate, String reminderTime, String category) {
        this.title = title;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime; // تعيين قيمة الوقت
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public String getReminderTime() {  
        return reminderTime;
    }

    public String getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public void setReminderTime(String reminderTime) {  
        this.reminderTime = reminderTime;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
