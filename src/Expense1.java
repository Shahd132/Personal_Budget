public class Expense1{
    private String category;
    private double amount;

    public Expense1(String category, double amount) {
        if (category == null || category.length() < 3 || category.length() > 50 || !category.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("Invalid category name. Must be between 3 and 50 characters and contain only letters, numbers, and spaces.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}