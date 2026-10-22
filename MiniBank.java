
import java.util.Objects;
import java.util.regex.Pattern;

public class MiniBank {

    // 1. Validator
    static class Validator {
        private static final Pattern MOBILE =
                Pattern.compile("^[6-9][0-9]{9}$");
        private static final Pattern EMAIL =
                Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        private static final Pattern PAN =
                Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
        private static final Pattern IFSC =
                Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

        public static boolean isValidMobile(String s) {
            return s != null && MOBILE.matcher(s).matches();
        }

        public static boolean isValidEmail(String s) {
            return s != null && EMAIL.matcher(s).matches();
        }

        public static boolean isValidPan(String s) {
            return s != null && PAN.matcher(s).matches();
        }

        public static boolean isValidIfsc(String s) {
            return s != null && IFSC.matcher(s).matches();
        }
    }

    // 2. Enum and record
    enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    record Command(
            TransactionType type,
            String accountNumber,
            long amount) {
    }

    // 3. CommandParser
    static class CommandParser {
        public static Command parse(String line) {
            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException("Command cannot be empty.");
            }

            String[] parts = line.trim().split("\\s+");

            if (parts.length != 3) {
                throw new IllegalArgumentException(
                        "Use: TYPE ACCOUNT_NUMBER AMOUNT");
            }

            TransactionType type;
            try {
                type = TransactionType.valueOf(parts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid transaction type.");
            }

            if (!Pattern.matches("AC[0-9]{4}", parts[1])) {
                throw new IllegalArgumentException(
                        "Invalid account number. Example: AC0001");
            }

            long amount;
            try {
                amount = Long.parseLong(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Amount must be a whole number.");
            }

            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "Amount must be greater than zero.");
            }

            return new Command(type, parts[1], amount);
        }
    }

    // 4. Customer and Address
    static class Customer {
        private String name;
        private String email;
        private String mobile;
        private final String customerId;
        private Address address;
        private static long counter = 100;

        public Customer(String name, String email,
                        String mobile, Address address) {
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.address = address;
            this.customerId = "CUST" + (++counter);
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getMobile() { return mobile; }
        public String getCustomerId() { return customerId; }
        public Address getAddress() { return address; }

        static class Address {
            private String line;
            private String city;
            private String pincode;

            public Address(String line, String city, String pincode) {
                this.line = line;
                this.city = city;
                this.pincode = pincode;
            }

            public String getLine() { return line; }
            public String getCity() { return city; }
            public String getPincode() { return pincode; }
        }
    }

    // 5. Abstract Account class
    static abstract class Account {
        private final String accountNumber;
        private String ownerName;
        private long balance;
        private boolean active = true;
        private static int counter = 0;

        private static String generateAccountNumber() {
            return String.format("AC%04d", ++counter);
        }

        public Account(String ownerName, long openingBalance) {
            if (ownerName == null || ownerName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Owner name cannot be empty.");
            }
            if (openingBalance < 0) {
                throw new IllegalArgumentException(
                        "Opening balance cannot be negative.");
            }

            this.accountNumber = generateAccountNumber();
            this.ownerName = ownerName;
            this.balance = openingBalance;
        }

        public Account(String ownerName) {
            this(ownerName, 0);
        }

        public abstract double interestRate();

        public abstract boolean canWithdraw(long amount);

        public void deposit(long amount) {
            if (!active || amount <= 0) {
                throw new IllegalArgumentException("Invalid deposit.");
            }
            balance = Math.addExact(balance, amount);
        }

        public boolean withdraw(long amount) {
            if (!active || amount <= 0 || !canWithdraw(amount)) {
                return false;
            }
            balance = Math.subtractExact(balance, amount);
            return true;
        }

        protected long getBalanceForWithdrawalCheck() {
            return balance;
        }

        public String getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public long getBalance() { return balance; }
        public boolean isActive() { return active; }

        @Override
        public String toString() {
            return "Account Number: " + accountNumber
                    + ", Owner: " + ownerName
                    + ", Balance: Rs. " + balance;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Account other)) return false;
            return accountNumber.equals(other.accountNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountNumber);
        }
    }

    // 6. SavingsAccount
    static class SavingsAccount extends Account {
        private long minBalance;

        public SavingsAccount(String name, long openingBalance,
                              long minBalance) {
            super(name, openingBalance);

            if (minBalance < 0 || minBalance > openingBalance) {
                throw new IllegalArgumentException(
                        "Minimum balance must be between zero and opening balance.");
            }
            this.minBalance = minBalance;
        }

        @Override
        public double interestRate() {
            return 4.0;
        }

        @Override
        public boolean canWithdraw(long amount) {
            return amount > 0
                    && getBalanceForWithdrawalCheck() - amount >= minBalance;
        }

        public long getMinBalance() {
            return minBalance;
        }
    }

    // 7. CurrentAccount
    static class CurrentAccount extends Account {
        private long overdraftLimit;

        public CurrentAccount(String name, long openingBalance,
                              long overdraftLimit) {
            super(name, openingBalance);

            if (overdraftLimit < 0) {
                throw new IllegalArgumentException(
                        "Overdraft limit cannot be negative.");
            }
            this.overdraftLimit = overdraftLimit;
        }

        @Override
        public double interestRate() {
            return 0.0;
        }

        @Override
        public boolean canWithdraw(long amount) {
            return amount > 0
                    && getBalanceForWithdrawalCheck() - amount
                    >= -overdraftLimit;
        }
    }

    // 8. FixedDepositAccount
    static class FixedDepositAccount extends Account {
        public FixedDepositAccount(String name, long openingBalance) {
            super(name, openingBalance);
        }

        @Override
        public double interestRate() {
            return 7.0;
        }

        @Override
        public boolean canWithdraw(long amount) {
            return false;
        }
    }

    // 9. StatementFormatter
    static class StatementFormatter {
        public static String buildStatement(Account account) {
            if (account == null) {
                throw new IllegalArgumentException(
                        "Account cannot be null.");
            }

            StringBuilder statement = new StringBuilder();
            statement.append("\n========== MINI BANK STATEMENT ==========\n");
            statement.append("Account Type   : ")
                    .append(account.getClass().getSimpleName()).append("\n");
            statement.append("Account Number : ")
                    .append(account.getAccountNumber()).append("\n");
            statement.append("Account Holder : ")
                    .append(account.getOwnerName()).append("\n");
            statement.append("Balance        : Rs. ")
                    .append(account.getBalance()).append("\n");
            statement.append("Interest Rate  : ")
                    .append(account.interestRate()).append("%\n");
            statement.append("Account Status : ")
                    .append(account.isActive() ? "Active" : "Inactive").append("\n");
            statement.append("=========================================");

            return statement.toString();
        }
    }

    // 10. Main method
    public static void main(String[] args) {
        System.out.println("========== MINIBANK ==========");

        // Validation tests
        System.out.println("\n--- Validation Tests ---");
        System.out.println("Mobile 9876543210: "
                + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile 12345: "
                + Validator.isValidMobile("12345"));
        System.out.println("Email riya@gmail.com: "
                + Validator.isValidEmail("riya@gmail.com"));
        System.out.println("PAN ABCDE1234F: "
                + Validator.isValidPan("ABCDE1234F"));
        System.out.println("IFSC SBIN0001234: "
                + Validator.isValidIfsc("SBIN0001234"));

        // Runtime polymorphism: Account reference, different objects
        Account[] accounts = {
            new SavingsAccount("Riya", 5000, 1000),
            new CurrentAccount("Aarav", 2000, 3000),
            new FixedDepositAccount("Diya", 10000)
        };

        System.out.println("\n--- Runtime Polymorphism ---");
        for (Account account : accounts) {
            System.out.println(account.getClass().getSimpleName()
                    + " interest rate: " + account.interestRate() + "%");

            // Pattern matching instanceof
            if (account instanceof SavingsAccount savings) {
                System.out.println("Minimum balance: Rs. "
                        + savings.getMinBalance());
            }
        }

        // Withdrawal rules
        System.out.println("\n--- Withdrawal Tests ---");
        System.out.println("Savings withdraw 3500: "
                + accounts[0].withdraw(3500));
        System.out.println("Savings balance: Rs. "
                + accounts[0].getBalance());

        System.out.println("Current withdraw 4000: "
                + accounts[1].withdraw(4000));
        System.out.println("Current balance: Rs. "
                + accounts[1].getBalance());

        System.out.println("Fixed deposit withdraw 100: "
                + accounts[2].withdraw(100));
        System.out.println("Fixed deposit balance: Rs. "
                + accounts[2].getBalance());

        // Parse and execute deposit command
        System.out.println("\n--- Command Parsing ---");
        try {
            Command command = CommandParser.parse("DEPOSIT AC0001 500");
            System.out.println("Type: " + command.type());
            System.out.println("Account: " + command.accountNumber());
            System.out.println("Amount: Rs. " + command.amount());

            if (command.type() == TransactionType.DEPOSIT
                    && command.accountNumber().equals(
                            accounts[0].getAccountNumber())) {
                accounts[0].deposit(command.amount());
                System.out.println("Deposit successful!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(StatementFormatter.buildStatement(accounts[0]));
        System.out.println("\nMiniBank practical completed.");
    }
}
