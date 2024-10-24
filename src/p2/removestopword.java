package p2;

import java.io.*;
import java.util.*;

public class removestopword {
    File stopfile = new File("stopwords.txt");
    Set<String> stopWords = new HashSet<>();

    public removestopword() {
        loadStopWords();
    }

    private void loadStopWords() {
        try (BufferedReader br = new BufferedReader(new FileReader(stopfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading stop words: " + e.getMessage());
        }
    }

    public File removeStopWord(String fileName) {
        File inputFile = new File(fileName);
        File outputFile = new File("filtered_" + inputFile.getName()); // Output file

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words
                StringBuilder filteredLine = new StringBuilder();

                // Filter out the stop words and remove punctuation
                for (String word : words) {
                    // Remove punctuation from the word
                    String cleanedWord = word.replaceAll("[\\p{Punct}]", "");

                    // Check if the cleaned word is not a stop word
                    if (!stopWords.contains(cleanedWord.toLowerCase()) && !cleanedWord.isEmpty()) {
                        filteredLine.append(cleanedWord).append(" ");
                    }
                }

                // Write the filtered line to the output file
                writer.write(filteredLine.toString().trim());
                writer.newLine();
            }

            System.out.println("Stop words removed. Filtered file created: " + outputFile.getName());

        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }

        // Return the filtered file
        return outputFile;
    }
}
