import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) {

        try {
            Path file1 = Path.of("log1.txt");
            Path file2 = Path.of("log2.txt");
            Path file3 = Path.of("log3.txt");

            Files.writeString(file1,
                    "INFO Server started\n" +
                    "ERROR Database connection failed\n" +
                    "INFO User logged in\n");

            Files.writeString(file2,
                    "INFO File uploaded\n" +
                    "ERROR File not found\n" +
                    "INFO User logged out\n");

            Files.writeString(file3,
                    "INFO Server running\n" +
                    "WARNING Low memory\n" +
                    "ERROR Server stopped\n");

            Path[] files = {file1, file2, file3};

            String keyword = "ERROR";

            int totalLines = 0;
            int keywordLines = 0;

            for (Path file : files) {

                List<String> lines = Files.readAllLines(file);

                int fileLines = 0;
                int fileKeywordLines = 0;

                for (String line : lines) {
                    totalLines++;
                    fileLines++;

                    if (line.contains(keyword)) {
                        keywordLines++;
                        fileKeywordLines++;
                    }
                }

                long size = Files.size(file);

                System.out.println("File: " + file);
                System.out.println("Size: " + size + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println("Keyword lines: " + fileKeywordLines);
                System.out.println();
            }

            System.out.println("Total lines: " + totalLines);
            System.out.println("Total lines containing \"" + keyword + "\": " + keywordLines);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}