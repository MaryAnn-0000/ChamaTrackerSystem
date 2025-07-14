package ChamaTracker;

import java.time.LocalDate;

// Represents a single contribution with amount and date
public class Contribution {
    private double amount;
    private LocalDate date;

    public Contribution(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Date: " + date;
    }
}
