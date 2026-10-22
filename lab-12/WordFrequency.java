import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class WordFrequency {

    public static void main(String[] args) {

        String paragraph = "Java is easy. Java is powerful. Java is popular.";

        // Convert paragraph into lowercase words
        paragraph = paragraph.toLowerCase().replaceAll("[^a-z\\s]", "");

        String[] words = paragraph.split("\\s+");

        // Count word frequency
        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        // Convert map entries into a list
        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        // Sort by frequency, highest first
        list.sort(
                Comparator.comparing(Map.Entry<String, Integer>::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey)
        );

        // Display result
        System.out.println("Word Frequency:");

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}