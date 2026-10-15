
import java.util.Scanner;

public class MiniBank {

    // Record to store bank information
    record BankInfo(String name, String branch) {}

    // Enum for menu options
    enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Bank information
        BankInfo bank = new BankInfo(
            "MiniBank",
            "Anand Branch"
        );

        System.out.println("================================");
        System.out.println("       " + bank.name());
        System.out.println("       " + bank.branch());
        System.out.println("================================");

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

            // Switch expression
            String message = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT
                    + " - to be implemented in a later lab";

                case 2 -> MenuOption.DEPOSIT
                    + " - to be implemented in a later lab";

                case 3 -> MenuOption.WITHDRAW
                    + " - to be implemented in a later lab";

                case 4 -> MenuOption.TRANSFER
                    + " - to be implemented in a later lab";

                case 5 -> "Exiting MiniBank...";

                default -> "Invalid choice! Please try again.";
            };

            System.out.println(message);

        } while (choice != 5);

        System.out.println("Thank you for using MiniBank!");
        sc.close();
    }
}
