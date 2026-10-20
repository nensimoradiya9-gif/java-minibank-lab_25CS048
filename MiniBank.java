
import java.util.Scanner;
import java.util.Objects;

public class MiniBank {

    // Customer class
    static class Customer implements Cloneable {

        private String name;
        private String email;
        private String mobile;
        private final String customerId;
        private Address address;

        private static long customerCounter = 100;

        private static String generateCustomerId() {
            customerCounter++;
            return "CUST" + customerCounter;
        }

        public Customer(String name, String email, String mobile, Address address) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.address = address;
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

        public Address getAddress() {
            return address;
        }

        // Nested Address class
        public static class Address {
            private String line;
            private String city;
            private String pincode;

            public Address(String line, String city, String pincode) {
                this.line = line;
                this.city = city;
                this.pincode = pincode;
            }

            public String getLine() {
                return line;
            }

            public String getCity() {
                return city;
            }

            public String getPincode() {
                return pincode;
            }
        }

        // Clone customer
        @Override
        public Customer clone() {
            try {
                Customer copy = (Customer) super.clone();

                // Copy Address also
                copy.address = new Address(
                    address.getLine(),
                    address.getCity(),
                    address.getPincode()
                );

                return copy;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
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

        // Readable account information
        @Override
        public String toString() {
            return "Account Number: " + accountNumber
                    + ", Owner: " + ownerName
                    + ", Balance: Rs. " + balance;
        }

        // Compare accounts using account number
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Account)) {
                return false;
            }

            Account other = (Account) o;

            return accountNumber.equals(other.accountNumber);
        }

        // Generate hash code using account number
        @Override
        public int hashCode() {
            return Objects.hash(accountNumber);
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

        // Create customer addresses
        Customer.Address a1 = new Customer.Address(
            "Station Road", "Anand", "388001"
        );

        Customer.Address a2 = new Customer.Address(
            "College Road", "Anand", "388002"
        );

        Customer.Address a3 = new Customer.Address(
            "Market Road", "Nadiad", "387001"
        );

        // Create customers
        Customer c1 = new Customer(
            "Riya", "riya@gmail.com", "9876543210", a1
        );

        Customer c2 = new Customer(
            "Amit", "amit@gmail.com", "9876543211", a2
        );

        Customer c3 = new Customer(
            "Neha", "neha@gmail.com", "9876543212", a3
        );

        // Create three accounts
        Account[] accounts = {
            new Account(c1.getName(), 5000),
            new Account(c2.getName()),
            new Account(c3.getName(), 3000)
        };

        // Deposit and withdrawal tests
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
            "Balance after failed withdrawal: Rs. "
                    + accounts[0].getBalance()
        );

        // Print accounts using toString()
        System.out.println("\n===== All Accounts =====");

        for (int i = 0; i < accounts.length; i++) {
            System.out.println(accounts[i]);
        }

        // Compare accounts using equals()
        System.out.println("\n===== Account Comparison =====");

        System.out.println(
            "Account 1 equals Account 2: "
                    + accounts[0].equals(accounts[1])
        );

        System.out.println(
            "Account 1 equals itself: "
                    + accounts[0].equals(accounts[0])
        );

        // Check object type using instanceof
        System.out.println("\n===== instanceof Test =====");

        Object obj = accounts[0];

        System.out.println(
            "Is obj an Account? " + (obj instanceof Account)
        );

        System.out.println(
            "Is obj a Customer? " + (obj instanceof Customer)
        );

        // Display customer address
        System.out.println("\n===== Customer Address =====");

        System.out.println("Customer: " + c1.getName());
        System.out.println("City: " + c1.getAddress().getCity());
        System.out.println("Pincode: " + c1.getAddress().getPincode());

        // Clone customer
        System.out.println("\n===== Clone Customer =====");

        Customer clonedCustomer = c1.clone();

        System.out.println("Original: " + c1.getCustomerId()
                + " - " + c1.getName());

        System.out.println("Clone: " + clonedCustomer.getCustomerId()
                + " - " + clonedCustomer.getName());

        System.out.println(
            "Same customer object? " + (c1 == clonedCustomer)
        );

        int choice;

        do {
            System.out.println("\n===== MiniBank Menu =====");
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
