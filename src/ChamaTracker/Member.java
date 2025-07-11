package ChamaTracker;

public class Member {
    private String id;
    private String name;
    private double totalContributed;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.totalContributed = 0.0;
    }

    public void addContribution(double amount) {
        this.totalContributed += amount;
    }

    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Total Contributed: " + totalContributed;
    }

    public double getTotalContributed() {
        return totalContributed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
