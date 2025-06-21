
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// Parv Patel 3137681
public class PartA_Driver {
    public static void main(String[] args) {
        Map<String, Integer> wordFrequency = new ProbeHashMap<>();
        Map<Character, Integer> charFrequency = new ProbeHashMap<>();
        Map<String, Integer> pronounFrequency = new ProbeHashMap<>();

        readText(wordFrequency, charFrequency, pronounFrequency);

        System.out.println("Text Analyzer");
        System.out.println("Total number of distinct words: " + wordFrequency.size());
        System.out.println("Total number of distinct letters: " + charFrequency.size());

        MyMapEntry<Character, Integer> maxCharEntry = findMaxLeast(charFrequency, true);
        MyMapEntry<Character, Integer> minCharEntry = findMaxLeast(charFrequency, false);
        MyMapEntry<String, Integer> maxWordEntry = findMaxLeast(wordFrequency, true);
        MyMapEntry<String, Integer> minWordEntry = findMaxLeast(wordFrequency, false);
        MyMapEntry<String, Integer> maxPronounEntry = findCategoryMaxLeast(pronounFrequency,
                List.of("i", "you", "he", "she", "it", "we", "they"), true);
        MyMapEntry<String, Integer> minPronounEntry = findCategoryMaxLeast(pronounFrequency,
                List.of("i", "you", "he", "she", "it", "we", "they"), false);

        System.out.println("Most occurring character: " + maxCharEntry.getKey() + ", " + maxCharEntry.getValue());
        System.out.println("Least occurring character: " + minCharEntry.getKey() + ", " + minCharEntry.getValue());
        System.out.println("Most occurring word: " + maxWordEntry.getKey() + ", " + maxWordEntry.getValue());
        System.out.println("Least occurring word: " + minWordEntry.getKey() + ", " + minWordEntry.getValue());
        System.out.println("Most occurring pronoun: " + maxPronounEntry.getKey() + ", " + maxPronounEntry.getValue());
        System.out.println("Least occurring pronoun: " + minPronounEntry.getKey() + ", " + minPronounEntry.getValue());
    }

    private static void readText(Map<String, Integer> wordFrequency, Map<Character, Integer> charFrequency,
            Map<String, Integer> pronounFrequency) {
        try (BufferedReader reader = new BufferedReader(new FileReader("PartA.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner f = new Scanner(line);
                f.useDelimiter("[^a-zA-Z]+");
                while (f.hasNext()) {
                    String word = f.next().toLowerCase();
                    if (!word.isEmpty()) {
                        Integer count = wordFrequency.get(word);
                        wordFrequency.put(word, count == null ? 1 : count + 1);
                        for (int i = 0; i < word.length(); i++) {
                            char c = word.charAt(i);
                            count = charFrequency.get(c);
                            charFrequency.put(c, count == null ? 1 : count + 1);
                        }
                        count = pronounFrequency.get(word);
                        if (count != null) {
                            pronounFrequency.put(word, count + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <K, V> MyMapEntry<K, V> findMaxLeast(Map<K, V> map, boolean findMax) {
        Iterable<Entry<K, V>> entries = map.entrySet();
        List<MyMapEntry<K, V>> sortedEntries = new ArrayList<>(entries);
        MergeSort.sort(sortedEntries, new OrderLettersByFrequency<>(e1, e2) {
            @SuppressWarnings("unchecked")
            V v1 = (V) e1.getValue();
            @SuppressWarnings("unchecked")
            V v2 = (V) e2.getValue();
            return ((Comparable<V>) v1).compareTo(v2);
        });
        return findMax ? sortedEntries.get(sortedEntries.size() - 1) : sortedEntries.get(0);
    }

    private static <K, V> MyMapEntry<K, V> findCategoryMaxLeast(Map<K, V> map, List<K> category, boolean findMax) {
        Map<K, V> categoryMap = new HashMap<>();
        for (K key : category) {
            V value = map.get(key);
            if (value != null) {
                categoryMap.put(key, value);
            }
        }
        return findMaxLeast(categoryMap, findMax);
    }
}
