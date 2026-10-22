import java.util.Scanner;
import java.util.Objects;
import java.util.regex.Pattern;

// Main class
public class MiniBank {

    // 1. Validator class
    static class Validator {

        private static final Pattern MOBILE_PATTERN =
                Pattern.compile("^[6-9][0-9]{9}$");

        private static final Pattern EMAIL_PATTERN =
                Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        private static final Pattern PAN_PATTERN =
                Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

        private static final Pattern IFSC_PATTERN =
                Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

        public static boolean isValidMobile(String mobile) {
            return mobile != null
                    && MOBILE_PATTERN.matcher(mobile).matches();
        }

        public static boolean isValidEmail(String email) {
            return email != null
                    && EMAIL_PATTERN.matcher(email).matches();
        }

        public static boolean isValidPan(String pan) {
            return pan != null
                    && PAN_PATTERN.matcher(pan).matches();
        }

        public static boolean isValidIfsc(String ifsc) {
            return ifsc != null
                    && IFSC_PATTERN.matcher(ifsc).matches();
        }
    }

    // 2. TransactionType enum
    enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

    // 3. Command record
    record Command(
            TransactionType type,
            String accountNumber,
            long amount
    ) {
    }

    // 4. CommandParser class
    static class CommandParser {

        public static Command parse(String line) {

            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Command cannot be empty."
                );
            }

            String[] parts = line.trim().split("\\s+");

            if (parts.length != 3) {
                throw new IllegalArgumentException(
                        "Use: TYPE ACCOUNT_NUMBER AMOUNT"
                );
            }

            TransactionType type;

            try {
                type = TransactionType.valueOf(
                        parts[0].toUpperCase()
                );
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Invalid transaction type."
                );
            }

            String accountNumber = parts[1];

            if (!Pattern.matches("AC[0-9]{4}", accountNumber)) {
                throw new IllegalArgumentException(
                        "Invalid account number. Example: AC0001"
                );
            }

            long amount;

            try {
                amount = Long.parseLong(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Amount must be a whole number."
                );
            }

            if (amount <= 0) {
                throw new IllegalArgumentException(
                        "Amount must be greater than zero."
                );
            }

            return new Command(type, accountNumber, amount);
        }
    }

    // 5. Customer class
    static class Customer {

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

        public Customer(
                String name,
                String email,
                String mobile,
                Address address
        ) {
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
        static class Address {

            private String line;
            private String city;
            private String pincode;

            public Address(
                    String line,
                    String city,
                    String pincode
            ) {
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
    }

    // 6. Account class
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
            if (openingBalance < 0) {
                throw new IllegalArgumentException(
                        "Opening balance cannot be negative."
                );
            }

            this.accountNumber = generateAccountNumber();
            this.ownerName = ownerName;
            this.balance = openingBalance;
            this.active = true;
        }

        // Constructor with zero balance
        public Account(String ownerName) {
            this(ownerName, 0);
        }

        public void deposit(long amount) {
            if (!active || amount <= 0) {
                throw new IllegalArgumentException(
                        "Invalid deposit."
                );
            }

            balance = Math.addExact(balance, amount);
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
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Account)) {
                return false;
            }

            Account other = (Account) obj;

            return accountNumber.equals(other.accountNumber);
        }
 // Generate hash code using account number
        @Override
        public int hashCode() {
            return Objects.hash(accountNumber);
        }
    }

    // 7. StatementFormatter class
    static class StatementFormatter {

        public static String buildStatement(Account account) {

            if (account == null) {
                throw new IllegalArgumentException(
                        "Account cannot be null."
                );
            }

            StringBuilder statement = new StringBuilder();

            statement.append("\n========== MINI BANK STATEMENT ==========\n");
            statement.append("Account Number : ")
                    .append(account.getAccountNumber())
                    .append("\n");

            statement.append("Account Holder : ")
                    .append(account.getOwnerName())
                    .append("\n");

            statement.append("Balance        : Rs. ")
                    .append(account.getBalance())
                    .append("\n");

            statement.append("Account Status : ")
                    .append(account.isActive() ? "Active" : "Inactive")
                    .append("\n");

            statement.append("=========================================");

            return statement.toString();
        }
    }

    // 8. Main method
    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("          MINIBANK");
        System.out.println("==================================");

        // Test mobile validation
        System.out.println("\n--- Mobile Validation ---");
        System.out.println(
                "9876543210: "
                + Validator.isValidMobile("9876543210")
        );
        System.out.println(
                "12345: "
                + Validator.isValidMobile("12345")
        );

        // Test email validation
        System.out.println("\n--- Email Validation ---");
        System.out.println(
                "riya@gmail.com: "
                + Validator.isValidEmail("riya@gmail.com")
        );
        System.out.println(
                "riya@gmail: "
                + Validator.isValidEmail("riya@gmail")
        );

        // Test PAN validation
        System.out.println("\n--- PAN Validation ---");
        System.out.println(
                "ABCDE1234F: "
                + Validator.isValidPan("ABCDE1234F")
        );
        System.out.println(
                "ABC123: "
                + Validator.isValidPan("ABC123")
        );

        // Test IFSC validation
        System.out.println("\n--- IFSC Validation ---");
        System.out.println(
                "SBIN0001234: "
                + Validator.isValidIfsc("SBIN0001234")
        );
        System.out.println(
                "SBI123: "
                + Validator.isValidIfsc("SBI123")
        );

        // Create an account
        Account account = new Account("Riya", 5000);

        // Parse a sample command
        System.out.println("\n--- Command Parsing ---");

        try {
            Command command = CommandParser.parse(
                    "DEPOSIT AC0001 500"
            );

            System.out.println("Transaction Type : " + command.type());
            System.out.println("Account Number   : " + command.accountNumber());
            System.out.println("Amount           : Rs. " + command.amount());

            // Execute the parsed deposit command
            if (command.type() == TransactionType.DEPOSIT
                    && command.accountNumber().equals(
                            account.getAccountNumber())) {

                account.deposit(command.amount());

                System.out.println("Deposit successful!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Print account statement
        System.out.println(
                StatementFormatter.buildStatement(account)
        );

        System.out.println("\nMiniBank practical completed.");
    }
}
