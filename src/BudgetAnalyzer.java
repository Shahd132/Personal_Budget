import java.util.List;

public class BudgetAnalyzer {
    public static String analyzeSpending(Budget budget, List<Expense> expenses) {
        double totalSpent = 0;
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(budget.getCategory())) {
                totalSpent += e.getAmount();
            }
        }

        double budgetAmount = budget.getAmount();
        double percentage = (totalSpent / budgetAmount) * 100;

        String status;
        if (totalSpent > budgetAmount) {
            status = "⚠️ You have exceeded your budget!";
        } else if (totalSpent == budgetAmount) {
            status = "✅ You have reached your budget limit.";
        } else {
            status = "🟢 You are within your budget.";
        }

        // رسم بياني نصي بسيط باستخدام علامات #
        int barLength = Math.min((int)(percentage / 10), 10); // عشان مايزودش عن 100%
        String bar = "#".repeat(barLength) + "-".repeat(10 - barLength); // مقياس من 10 وحدات

        return String.format(
            "📊 Spending Analysis for '%s':\n" +
            "- Spent: $%.2f\n" +
            "- Budget: $%.2f\n" +
            "- Usage: %.1f%%\n" +
            "- Visual: [%s]\n" +
            "%s",
            budget.getCategory(), totalSpent, budgetAmount, percentage, bar, status
        );
    }
}

