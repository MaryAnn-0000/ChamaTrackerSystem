package ChamaTracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chama chama = new Chama();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Chama Tracker Menu ---");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Record Contribution");
            System.out.println("4. Add Expense");
            System.out.println("5. Show Financial Summary");
            System.out.println("6. List Members");
            System.out.println("7. List Expenses");
            System.out.println("8. Exit");
            System.out.println("9. Reactivate Member"); // New menu option
            System.out.println("10. List Member Contributions"); // New menu option
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {

                case 1:
                    System.out.print("Enter Member ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    if (chama.addMember(id, name)) {
                        System.out.println("Member added successfully.");
                    } else {
                        System.out.println("Member ID already exists.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Member ID to remove: ");
                    String removeId = scanner.nextLine();
                    // Now flags as INACTIVE
                    if (chama.removeMember(removeId)) {
                        System.out.println("Member flagged as INACTIVE.");
                    } else {
                        System.out.println("Member not found or already INACTIVE.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter Contribution Amount: ");
                    double amount;
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Invalid input. Enter a number: ");
                        scanner.next();
                    }
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount < 0) {
                        System.out.println("Error: Contribution amount cannot be negative.");
                    } else if (chama.recordContribution(memberId, amount)) {
                        System.out.println("Contribution recorded.");
                    } else {
                        System.out.println("Member not found, INACTIVE, or invalid amount.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Expense Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Expense Amount: ");
                    double expAmount;
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Invalid input. Enter a number: ");
                        scanner.next();
                    }
                    expAmount = scanner.nextDouble();
                    scanner.nextLine();
                    if (expAmount < 0) {
                        System.out.println("Error: Expense amount cannot be negative.");
                    } else if (!chama.addExpense(desc, expAmount)) {
                        double balance = chama.getCurrentBalance();
                        if (expAmount > balance) {
                            System.out.println("Error: Expense exceeds current balance (" + balance + ").");
                        } else {
                            System.out.println("Error: Invalid expense amount.");
                        }
                    } else {
                        System.out.println("Expense added.");
                    }
                    break;
                case 5:
                    chama.showFinancialSummary();
                    break;
                case 6:
                    chama.listMembers();
                    break;
                case 7:
                    chama.listExpenses();
                    break;
                case 8:
                    System.out.println("Exiting. Goodbye!");
                    break;
                case 9:
                    System.out.print("Enter Member ID to reactivate: ");
                    String reactivateId = scanner.nextLine();
                    if (chama.reactivateMember(reactivateId)) {
                        System.out.println("Member reactivated successfully.");
                    } else {
                        Member m = chama.getMemberById(reactivateId);
                        if (m == null) {
                            System.out.println("Member not found.");
                        } else if (m.getStatus() == Status.ACTIVE) {
                            System.out.println("Member is already ACTIVE.");
                        } else {
                            System.out.println("Unable to reactivate member.");
                        }
                    }
                    break;
                case 10:
                    chama.listAllMembersContributions();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);
        scanner.close();
    }
}
