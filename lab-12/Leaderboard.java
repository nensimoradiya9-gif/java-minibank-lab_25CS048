import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {

    public static void main(String[] args) {

        // Store player names and scores
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Riya", 95);
        scores.put("Amit", 88);
        scores.put("Neha", 95);
        scores.put("Karan", 76);
        scores.put("Priya", 91);

        // Convert map entries into a list
        List<Map.Entry<String, Integer>> players =
                new ArrayList<>(scores.entrySet());

        // Sort by score descending, then name ascending
        players.sort(
                Comparator.comparing(Map.Entry<String, Integer>::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey)
        );

        int topN = 3;

        System.out.println("Top " + topN + " Players:");

        for (int i = 0; i < topN && i < players.size(); i++) {
            Map.Entry<String, Integer> player = players.get(i);

            System.out.println(
                    (i + 1) + ". " + player.getKey()
                    + " - " + player.getValue()
            );
        }
    }
}
