import java.util.HashSet;
import java.util.LinkedHashSet;

public class UniqueVisitors {

    public static void main(String[] args) {

        String[] logins = {
            "Riya", "Amit", "Neha", "Riya",
            "Karan", "Amit", "Priya", "Neha"
        };

        // HashSet stores unique names
        HashSet<String> uniqueUsers = new HashSet<>();

        // LinkedHashSet stores unique names in first-seen order
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>();

        for (int i = 0; i < logins.length; i++) {
            uniqueUsers.add(logins[i]);
            orderedUsers.add(logins[i]);
        }

        System.out.println("Total login records: " + logins.length);
        System.out.println("Unique visitors: " + uniqueUsers.size());

        System.out.println("First three distinct visitors:");

        int count = 0;

        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}
