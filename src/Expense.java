public class Expense extends Transaction {
    private String category;

    public Expense(String source, double amount, String date, String category) {
        super(source, amount, date);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + " | Category: " + category + " | Date: " + date;
    }

    @Override
    public String toFileString() {
        return source + "," + amount + "," + date + "," + category;
    }
}
