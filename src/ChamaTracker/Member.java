package ChamaTracker;

import java.util.*;
import java.time.LocalDate;

// Member now tracks individual contributions and supports status management
public class Member implements StatusTrackable {
    private String id;
    private String name;
    private List<Contribution> contributions; // List of all contributions
    private Status status; // ACTIVE or INACTIVE

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.contributions = new ArrayList<>();
        this.status = Status.ACTIVE; // Default to ACTIVE
    }

    // Add a new contribution with the current date
    public void addContribution(double amount) {
        contributions.add(new Contribution(amount, LocalDate.now()));
    }

    // Get the total contributed by summing all contributions
    public double getTotalContributed() {
        double total = 0.0;
        for (Contribution c : contributions) {
            total += c.getAmount();
        }
        return total;
    }

    // Get all contributions for display or processing
    public List<Contribution> getContributions() {
        return contributions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Implement StatusTrackable interface
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Show status and total in member display
    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Total Contributed: " + getTotalContributed() + ", Status: "
                + status;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + status + ")";
    }
}
