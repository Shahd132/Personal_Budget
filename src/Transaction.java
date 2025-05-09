public abstract class Transaction {
    protected String source;
    protected double amount;
    protected String date;

    public Transaction(String source, double amount, String date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getSource() { return source; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }

    public abstract String toFileString();
}
