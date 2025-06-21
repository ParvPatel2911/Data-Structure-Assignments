
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//Parv Patel 3137681

public class PartA_Driver {
    public static void main(String[] args) {
        // Create a ProbeHashMap to store word frequencies
        ProbeHashMap<String, Integer> wordFrequencyMap = new ProbeHashMap<>();

        // Create a ProbeHashMap to store character frequencies
        ProbeHashMap<Character, Integer> characterFrequencyMap = new ProbeHashMap<>();

        // Read the file "PartA.txt"
        try {
            File file = new File("PartA.txt");
            Scanner scanner = new Scanner(file);

            // Use delimiter to remove sentence punctuation
            scanner.useDelimiter("[^a-zA-Z']+");

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase(); // Convert to lowercase for consistency
                // Update word frequency map
                int frequency = wordFrequencyMap.getOrDefault(word, 0);
                wordFrequencyMap.put(word, frequency + 1);

                // Update character frequency map
                for (char c : word.toCharArray()) {
                    int charFrequency = characterFrequencyMap.getOrDefault(c, 0);
                    characterFrequencyMap.put(c, charFrequency + 1);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        // Calculate total number of distinct words
        int totalDistinctWords = wordFrequencyMap.size();

        // Calculate total number of distinct letters
        int totalDistinctLetters = characterFrequencyMap.size();

        // Find and display words with maximum and least frequencies
        Entry<String, Integer> maxWord = findMaxLeast(wordFrequencyMap, true);
        Entry<String, Integer> leastWord = findMaxLeast(wordFrequencyMap, false);

        // Find and display characters with maximum and least frequencies
        Entry<Character, Integer> maxChar = findMaxLeast(characterFrequencyMap, true);
        Entry<Character, Integer> leastChar = findMaxLeast(characterFrequencyMap, false);

        // Create a list of pronouns
        List<String> pronouns = Arrays.asList("he", "she", "it", "they", "them", "we", "us", "you", "i", "me");

        // Find and display pronouns with maximum and least frequencies
        Entry<String, Integer> maxPronoun = findCategoryMaxLeast(wordFrequencyMap, pronouns, true);
        Entry<String, Integer> leastPronoun = findCategoryMaxLeast(wordFrequencyMap, pronouns, false);

        // Display results
        System.out.println("Text Analyzer");
        System.out.println("Total number of distinct words: " + totalDistinctWords);
        System.out.println("Total number of distinct letters: " + totalDistinctLetters);
        System.out.println("Most occurring character: " + maxChar.getKey() + ", " + maxChar.getValue());
        System.out.println("Least occurring character: " + leastChar.getKey() + ", " + leastChar.getValue());
        System.out.println("Most occurring word: " + maxWord.getKey() + ", " + maxWord.getValue());
        System.out.println("Least occurring word: " + leastWord.getKey() + ", " + leastWord.getValue());
        System.out.println("Most occurring pronoun: " + maxPronoun.getKey() + ", " + maxPronoun.getValue());
        System.out.println("Least occurring pronoun: " + leastPronoun.getKey() + ", " + leastPronoun.getValue());
    }

    public static <K, V> Entry<K, Integer> findMaxLeast(Map<K, Integer> map, boolean findMax) {
        Entry<K, Integer> maxLeastEntry = null;

        for (Entry<K, Integer> entry : map.entrySet()) {
            if (maxLeastEntry == null ||
                    (findMax && entry.getValue() > maxLeastEntry.getValue()) ||
                    (!findMax && entry.getValue() < maxLeastEntry.getValue())) {
                maxLeastEntry = entry;
            }
        }

        return maxLeastEntry;
    }

    public static <K, V> Entry<K, Integer> findCategoryMaxLeast(Map<K, Integer> map, List<K> keys, boolean findMax) {
        Entry<K, Integer> maxLeastEntry = null;

        for (K key : keys) {
            Integer frequency = map.get(key);
            if (frequency == null) {
                frequency = 0; // Default frequency to 0 if key doesn't exist
            }
            if (maxLeastEntry == null ||
                    (findMax && (maxLeastEntry.getValue() == null || frequency > maxLeastEntry.getValue())) ||
                    (!findMax && (maxLeastEntry.getValue() == null || frequency < maxLeastEntry.getValue()))) {
                maxLeastEntry = new AbstractMap.SimpleEntry<>(key, frequency);
            }
        }

        return maxLeastEntry;
    }

}
