package ChamaTracker;

import java.util.*;

public class Chama {

    // List all contributions for all members
    public void listAllMembersContributions() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        for (Member member : members) {
            System.out.println("--- Contributions for " + member.getName() + " (" + member.getId() + ") ---");
            if (member.getContributions().isEmpty()) {
                System.out.println("No contributions found.");
            } else {
                for (Contribution c : member.getContributions()) {
                    System.out.println(c);
                }
                System.out.println("Total for " + member.getName() + ": " + member.getTotalContributed());
            }
            System.out.println();
        }
    }

    // List all contributions for a specific member by ID
    public void listMemberContributions(String memberId) {
        Member member = memberMap.get(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        System.out.println("--- Contributions for " + member.getName() + " ---");
        if (member.getContributions().isEmpty()) {
            System.out.println("No contributions found.");
        } else {
            for (Contribution c : member.getContributions()) {
                System.out.println(c);
            }
        }
    }

    private List<Member> members;
    private List<Expense> expenses;
    private Map<String, Member> memberMap;

    public Chama() {
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        memberMap = new HashMap<>();
    }

    public boolean addMember(String id, String name) {
        if (memberMap.containsKey(id)) {
            return false; // Duplicate ID
        }
        Member member = new Member(id, name);
        members.add(member);
        memberMap.put(id, member);
        return true;
    }

    // Only ACTIVE members can contribute, and amount must be positive
    public boolean recordContribution(String memberId, double amount) {
        Member member = memberMap.get(memberId);
        if (member == null || member.getStatus() != Status.ACTIVE) {
            return false;
        }
        if (amount < 0) {
            return false; // Negative not allowed
        }
        member.addContribution(amount);
        return true;
    }

    // Add expense if amount is positive and does not exceed balance
    public boolean addExpense(String description, double amount) {
        if (amount < 0)
            return false; // Negative not allowed
        double balance = calculateCurrentBalance();
        if (amount > balance)
            return false; // Exceeds balance
        expenses.add(new Expense(description, amount));
        return true;
    }

    public double calculateTotalContributions() {
        double total = 0.0;
        for (Member m : members) {
            total += m.getTotalContributed();
        }
        return total;
    }

    public double calculateTotalExpenses() {
        double total = 0.0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public double calculateCurrentBalance() {
        return calculateTotalContributions() - calculateTotalExpenses();
    }

    public void showFinancialSummary() {
        System.out.println("--- Financial Summary ---");
        System.out.println("Total Contributions: " + calculateTotalContributions());
        System.out.println("Total Expenses: " + calculateTotalExpenses());
        System.out.println("Current Balance: " + calculateCurrentBalance());
    }

    public void listMembers() {
        System.out.println("--- Members ---");
        if (members.isEmpty()) {
            System.out.println("No members found");
        } else {
            for (Member m : members) {
                System.out.println(m.getDetails());
            }
        }

    }

    public void listExpenses() {
        System.out.println("--- Expenses ---");
        if (expenses.isEmpty()) {
            System.out.println("No expenses found");
        } else {
            for (Expense e : expenses) {
                System.out.println(e.getDetails());
            }
        }

    }

    // Flag member as INACTIVE instead of removing
    public boolean removeMember(String id) {
        Member member = memberMap.get(id);
        if (member != null && member.getStatus() == Status.ACTIVE) {
            member.setStatus(Status.INACTIVE);
            return true;
        }
        return false;
    }

    // Expose these for Main.java validation
    public double getTotalContributions() {
        return calculateTotalContributions();
    }

    public double getTotalExpenses() {
        return calculateTotalExpenses();
    }

    public double getCurrentBalance() {
        return calculateCurrentBalance();
    }

    // Reactivate an INACTIVE member by ID
    public boolean reactivateMember(String id) {
        Member member = memberMap.get(id);
        if (member == null) {
            return false; // Not found
        }
        if (member.getStatus() == Status.ACTIVE) {
            return false; // Already active
        }
        member.setStatus(Status.ACTIVE);
        return true;
    }

    // Get member by ID (for menu feedback)
    public Member getMemberById(String id) {
        return memberMap.get(id);
    }

}
