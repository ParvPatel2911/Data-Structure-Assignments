import java.util.Comparator;

// Parv Patel 3137681
public class OrderByLongitude<K, V> implements Comparator<PostalCode> {

    @Override
    public int compare(PostalCode code1, PostalCode code2) {
        // Compare postal codes based on their longitudes
        if (code1.getLongitude() < code2.getLongitude()) {
            return -1; // code1 is west of code2
        } else if (code1.getLongitude() > code2.getLongitude()) {
            return 1; // code1 is east of code2
        } else {
            return 0; // Longitudes are equal
        }
    }
}