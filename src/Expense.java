// public class Expense extends Transaction {
//     private String category;

//     public Expense(String source, double amount, String date, String category) {
//         super(source, amount, date);
//         this.category = category;
//     }

//     public String getCategory() {
//         return category;
//     }

//     @Override
//     public String toString() {
//         return "Amount: " + amount + " | Category: " + category + " | Date: " + date;
//     }

//     @Override
//     public String toFileString() {
//         return source + "," + amount + "," + date + "," + category;
//     }
// }

public class Expense extends Transaction {
    private String category;

    public Expense(String source, double amount, String date, String category) {
        super(source, validateAmount(amount), date);
        this.category = validateCategory(category);
    }
    public Expense(String category, double amount) {
    super("Unknown", validateAmount(amount), "Unknown");
    this.category = validateCategory(category);
}


    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    private static String validateCategory(String category) {
        if (category == null || category.length() < 3 || category.length() > 50 || !category.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("Invalid category name. Must be between 3 and 50 characters and contain only letters, numbers, and spaces.");
        }
        return category;
    }

    private static double validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        return amount;
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

