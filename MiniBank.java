
import java.util.Scanner;

public class MiniBank {

    // Customer class
    static class Customer {
        private String name;
        private String email;
        private String mobile;
        private final String customerId;

        private static long customerCounter = 100;

        private static String generateCustomerId() {
            customerCounter++;
            return "CUST" + customerCounter;
        }

        public Customer(String name, String email, String mobile) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.customerId = generateCustomerId();
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getCustomerId() {
            return customerId;
        }
    }

    // Account class
    static class Account {
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active;

        private static int accountCounter = 0;

        private static String generateAccountNumber() {
            accountCounter++;
            return String.format("AC%04d", accountCounter);
        }

        // Constructor with opening balance
        public Account(String ownerName, long openingBalance) {
            this.accountNumber = generateAccountNumber();
            this.ownerName = ownerName;
            this.balance = Math.max(0, openingBalance);
            this.active = true;
        }

        // Constructor with zero balance
        public Account(String ownerName) {
            this(ownerName, 0);
        }

        public void deposit(long amount) {
            if (!active || amount <= 0) {
                System.out.println("Invalid deposit!");
                return;
            }

            balance += amount;
        }

        public boolean withdraw(long amount) {
            if (!active || amount <= 0 || amount > balance) {
                return false;
            }

            balance -= amount;
            return true;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public long getBalance() {
            return balance;
        }

        public boolean isActive() {
            return active;
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Bank information
        String bankName = "MiniBank";
        String branch = "Anand Branch";

        System.out.println("================================");
        System.out.println("       " + bankName);
        System.out.println("       " + branch);
        System.out.println("================================");

        // Create customers
        Customer c1 = new Customer(
            "Riya", "riya@gmail.com", "9876543210"
        );

        Customer c2 = new Customer(
            "Amit", "amit@gmail.com", "9876543211"
        );

        Customer c3 = new Customer(
            "Neha", "neha@gmail.com", "9876543212"
        );

        // Create three accounts
        Account[] accounts = {
            new Account(c1.getName(), 5000),
            new Account(c2.getName()),
            new Account(c3.getName(), 3000)
        };

        // Test deposit and withdrawal
        accounts[0].deposit(2000);
        System.out.println(
            "Balance after deposit: Rs. " + accounts[0].getBalance()
        );

        boolean result1 = accounts[0].withdraw(3000);
        System.out.println("Withdrawal successful: " + result1);
        System.out.println(
            "Balance after withdrawal: Rs. " + accounts[0].getBalance()
        );

        boolean result2 = accounts[0].withdraw(10000);
        System.out.println("Withdrawal successful: " + result2);
        System.out.println(
            "Balance after failed withdrawal: Rs. " + accounts[0].getBalance()
        );

        // Display all accounts
        System.out.println("\\n===== All Accounts =====");

        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account Number: " + accounts[i].getAccountNumber());
            System.out.println("Owner: " + accounts[i].getOwnerName());
            System.out.println("Balance: Rs. " + accounts[i].getBalance());
            System.out.println("Active: " + accounts[i].isActive());
            System.out.println("------------------------");
        }

        int choice;

        do {
            System.out.println("\\n===== MiniBank Menu =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            String message = switch (choice) {
                case 1 -> "OPEN_ACCOUNT - to be implemented in a later lab";
                case 2 -> "DEPOSIT - to be implemented in a later lab";
                case 3 -> "WITHDRAW - to be implemented in a later lab";
                case 4 -> "TRANSFER - to be implemented in a later lab";
                case 5 -> "Exiting MiniBank...";
                default -> "Invalid choice! Please try again.";
            };

            System.out.println(message);

        } while (choice != 5);

        System.out.println("Thank you for using MiniBank!");
        sc.close();
    }
}