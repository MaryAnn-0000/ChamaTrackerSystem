package ChamaTracker;

import java.util.*;

public class Chama {
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

    public boolean recordContribution(String memberId, double amount) {
        Member member = memberMap.get(memberId);
        if (member == null) {
            return false;
        }
        member.addContribution(amount);
        return true;
    }

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(description, amount));
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
        for (Member m : members) {
            System.out.println(m.getDetails());
        }
    }

    public void listExpenses() {
        System.out.println("--- Expenses ---");
        for (Expense e : expenses) {
            System.out.println(e.getDetails());
        }
    }

    public boolean removeMember(String id) {
        Member removed = memberMap.remove(id);
        if (removed != null) {
            members.remove(removed);
            return true;
        }
        return false;
    }
}
