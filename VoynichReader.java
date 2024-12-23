/** Project: Voynich Manuscript
 * Purpose Details: Deciphering text
 * Course: IST 242
 * Author: Algassim Bah
 * Date Developed: 12/13/24
 * Last Date Changed: 12/13/24
 * Rev: 12/13/24

 */
import java.io.*;
import java.util.*;

public class VoynichReader {
    public static void main(String[] args) {
        // File paths for the manuscript pages
        String[] filePaths = {"capture/Manuscript.txt"};

        // Load and process multiple pages of ciphertext
        StringBuilder fullText = new StringBuilder();
        System.out.println("Processing Pages:");
        for (String filePath : filePaths) {
            System.out.println("Processing: " + filePath);
            fullText.append(loadCiphertext(filePath)).append(" ");
        }

        String manuscript = fullText.toString().trim();
        if (manuscript.isEmpty()) {
            System.out.println("Error: Manuscript pages are empty or not found.");
            return;
        }

        // Step 1: Character frequency analysis
        Map<Character, Integer> charFrequency = analyzeCharacterFrequency(manuscript);
        System.out.println("\nCharacter Frequency:");
        charFrequency.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue()) // Sort by frequency
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Step 2: Foreign language frequency analysis
        String language = "Latin"; // Assume we're using Latin for analysis
        System.out.println("\nPerforming frequency analysis using: " + language);
        performFrequencyAnalysis(charFrequency);

        // Step 3: Word look-up and translation
        System.out.println("\nPerforming word look-up and translation...");
        Map<String, List<String>> dictionaryTable = loadDictionaryTable();
        List<String> translatedWords = translateWords(cleanAndSplitText(manuscript), dictionaryTable);
        System.out.println("Translation Status: Complete");
        translatedWords.forEach(System.out::println);

        // Step 4: Shift cipher analysis
        System.out.println("\nPerforming shift cipher analysis...");
        performShiftCipher(manuscript, dictionaryTable);
    }

    // Load ciphertext from a file
    public static String loadCiphertext(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return content.toString().trim();
    }

    // Perform frequency analysis on characters
    public static Map<Character, Integer> analyzeCharacterFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }
        return frequency;
    }

    // Perform foreign language frequency analysis
    public static void performFrequencyAnalysis(Map<Character, Integer> charFrequency) {
        // Example: Latin language character frequency
        charFrequency.forEach((character, frequency) -> {
            // Simulated statistical comparison logic
            System.out.println(character + ": appears " + frequency + " times");
        });
    }

    // Clean and split text into words
    public static List<String> cleanAndSplitText(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase(); // Remove non-alphabets
        return new ArrayList<>(Arrays.asList(cleanedText.split("\\s+")));
    }

    // Load dictionary table for translation
    public static Map<String, List<String>> loadDictionaryTable() {
        Map<String, List<String>> dictionaryTable = new HashMap<>();
        dictionaryTable.put("bread", Arrays.asList("pan", "panis"));
        dictionaryTable.put("plant", Arrays.asList("planta"));
        dictionaryTable.put("herb", Arrays.asList("herba"));
        return dictionaryTable;
    }

    // Translate words using the dictionary table
    public static List<String> translateWords(List<String> words, Map<String, List<String>> dictionaryTable) {
        List<String> translatedWords = new ArrayList<>();
        for (String word : words) {
            if (dictionaryTable.containsKey(word)) {
                translatedWords.add(word + " -> " + String.join("/", dictionaryTable.get(word)));
            } else {
                translatedWords.add(word + " -> (no match)");
            }
        }
        return translatedWords;
    }

    // Perform shift cipher analysis
    public static void performShiftCipher(String text, Map<String, List<String>> dictionaryTable) {
        for (int shift = 1; shift <= 25; shift++) {
            String shiftedText = shiftText(text, shift);
            List<String> words = cleanAndSplitText(shiftedText);
            long matchCount = words.stream().filter(dictionaryTable::containsKey).count();
            System.out.println("Shift " + shift + ": " + matchCount + " matches found.");
        }
    }

    // Shift text by a given number
    public static String shiftText(String text, int shift) {
        StringBuilder shifted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                shifted.append((char) ((c - base + shift) % 26 + base));
            } else {
                shifted.append(c);
            }
        }
        return shifted.toString();
    }
}
