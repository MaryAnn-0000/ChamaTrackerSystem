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
                    if (chama.removeMember(removeId)) {
                        System.out.println("Member removed successfully.");
                    } else {
                        System.out.println("Member not found.");
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
                    if (chama.recordContribution(memberId, amount)) {
                        System.out.println("Contribution recorded.");
                    } else {
                        System.out.println("Member not found.");
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
                    chama.addExpense(desc, expAmount);
                    System.out.println("Expense added.");
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
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
        scanner.close();
    }
}