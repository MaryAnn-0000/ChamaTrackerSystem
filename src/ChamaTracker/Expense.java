package ChamaTracker;

public class Expense {
    private String description;
    private double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDetails() {
        return "Description: " + description + ", Amount: " + amount;
    }

    public double getAmount() {
        return amount;
    }
}
