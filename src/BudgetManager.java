import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<Budget> budgets;
    private List<Expense1> expenses;

    public BudgetManager() {
        this.budgets = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // إضافة ميزانية جديدة
    public void addBudget(Budget budget) {
        this.budgets.add(budget);
    }

    // إضافة مصروف جديد
    public void addExpense(Expense1 expense) {
        this.expenses.add(expense);
    }

    // تحليل الإنفاق لمقارنته بالميزانية
    public String analyzeSpendingForCategory(String category) {
        Budget budget = findBudgetByCategory(category);
        if (budget != null) {
            return BudgetAnalyzer.analyzeSpending(budget, expenses);
        } else {
            return "No budget set for category: " + category;
        }
    }

    // العثور على الميزانية لفئة معينة
    private Budget findBudgetByCategory(String category) {
        for (Budget b : budgets) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                return b;
            }
        }
        return null;
    }

    // الحصول على كل الميزانيات
    public List<Budget> getAllBudgets() {
        return budgets;
    }

    // الحصول على كل المصروفات
    public List<Expense1> getAllExpenses() {
        return expenses;
    }
}

