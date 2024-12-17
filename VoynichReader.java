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
        // File path for the ciphertext file
        String filePath = "capture/manuscript.txt";

        // Load ciphertext from file
        String ciphertext = loadCiphertext(filePath);
        if (ciphertext.isEmpty()) {
            System.out.println("Error: Ciphertext file is empty or not found.");
            return;
        }

        // Step 1: Clean and split text into words
        List<String> words = cleanAndSplitText(ciphertext);

        // Step 2: Perform frequency analysis
        Map<Character, Integer> charFrequency = analyzeCharacterFrequency(words);
        Map<String, Integer> wordFrequency = analyzeWordFrequency(words);

        // Print character frequency
        System.out.println("Character Frequency:");
        charFrequency.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue()) // Sort by frequency
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Print top 10 most common words
        System.out.println("\nMost Common Words:");
        wordFrequency.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue()) // Sort by frequency
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Step 3: Define a cipher map for decryption
        Map<Character, Character> cipherMap = new HashMap<>();
        cipherMap.put('o', 'a');
        cipherMap.put('e', 'e');
        cipherMap.put('c', 't');
        cipherMap.put('h', 'o');
        cipherMap.put('y', 'n');
        cipherMap.put('p', 'r');
        cipherMap.put('.', ' ');

        // Step 4: Decrypt the text
        List<String> decryptedWords = decryptText(words, cipherMap);
        String decryptedText = String.join(" ", decryptedWords);

        System.out.println("\nDecrypted Text:");
        System.out.println(decryptedText);

        // Step 5: Perform language-based word analysis
        Map<String, List<String>> dictionaryTable = loadDictionaryTable();
        List<String> translatedWords = translateWords(decryptedWords, dictionaryTable);

        System.out.println("\nTranslated Words:");
        translatedWords.forEach(System.out::println);
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

    // Clean and split text into words
    public static List<String> cleanAndSplitText(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z.\\-]", ""); // Keep letters, periods, and dashes
        String[] words = cleanedText.split("\\.");
        return new ArrayList<>(Arrays.asList(words));
    }

    // Perform frequency analysis on characters
    public static Map<Character, Integer> analyzeCharacterFrequency(List<String> words) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }
        return frequency;
    }

    // Perform frequency analysis on words
    public static Map<String, Integer> analyzeWordFrequency(List<String> words) {
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        return frequency;
    }

    // Decrypt a single word using the cipher map
    public static String decryptWord(String word, Map<Character, Character> cipherMap) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : word.toCharArray()) {
            decrypted.append(cipherMap.getOrDefault(c, c)); // Use mapped char or original if not in map
        }
        return decrypted.toString();
    }

    // Decrypt the entire text
    public static List<String> decryptText(List<String> words, Map<Character, Character> cipherMap) {
        List<String> decryptedWords = new ArrayList<>();
        for (String word : words) {
            decryptedWords.add(decryptWord(word, cipherMap));
        }
        return decryptedWords;
    }

    // Load dictionary table for different languages
    public static Map<String, List<String>> loadDictionaryTable() {
        Map<String, List<String>> dictionaryTable = new HashMap<>();
        dictionaryTable.put("ata", Arrays.asList("father", "data"));
        dictionaryTable.put("ano", Arrays.asList("year", "ring"));
        dictionaryTable.put("che", Arrays.asList("what", "who"));
        dictionaryTable.put("thy", Arrays.asList("your", "thy"));
        dictionaryTable.put("ory", Arrays.asList("story", "history"));
        return dictionaryTable;
    }

    // Translate decrypted words using the dictionary table
    public static List<String> translateWords(List<String> decryptedWords, Map<String, List<String>> dictionaryTable) {
        List<String> translatedWords = new ArrayList<>();
        for (String word : decryptedWords) {
            if (dictionaryTable.containsKey(word)) {
                translatedWords.add(String.join("/", dictionaryTable.get(word)));
            } else {
                translatedWords.add(word); // If no translation, keep the original word
            }
        }
        return translatedWords;
    }
}

