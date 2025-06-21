import java.util.Comparator;
import java.util.Map;

// Parv Patel 3137681
public class OrderLettersByFrequency<K, V> implements Comparator<Map.Entry<Character, Integer>> {

    @Override
    public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
        int freqComparison = entry1.getValue().compareTo(entry2.getValue());

        // If frequencies are different, order by frequency
        if (freqComparison != 0) {
            return freqComparison;
        } else {
            // If frequencies are the same, order by natural ordering of letters
            return entry1.getKey().compareTo(entry2.getKey());
        }
    }
}
