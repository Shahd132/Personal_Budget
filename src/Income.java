public class Income extends Transaction {

    public Income(String source, double amount, String date) {
        super(source, amount, date);
    }

    @Override
    public String toFileString() {
        return "Income," + source + "," + amount + "," + date;
    }
}

