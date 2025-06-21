import java.util.Comparator;
import java.util.LinkedList;

// Parv Patel 3137681
public class QuickSort {
    /** Quick-sort contents of a queue. */
    public static <K> void quickSort(Queue<K> s, Comparator<?> comparator) {
        int n = s.size();
        if (n < 2)
            return; // queue is trivially sorted

        // divide
        K pivot = s.first(); // using first as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();

        while (!s.isEmpty()) {
            // divide original into L, E, and G
            K element = s.dequeue();
            int c = comparator.compare(element, pivot);
            if (c < 0) // element is less than pivot
                L.enqueue(element);
            else if (c == 0) // element is equal to pivot
                E.enqueue(element);
            else // element is greater than pivot
                G.enqueue(element);
        }

        // conquer (by recursively sorting subarrays)
        quickSort(L, comparator);
        quickSort(G, comparator);

        // concatenate results
        while (!L.isEmpty())
            s.enqueue(L.dequeue());
        while (!E.isEmpty())
            s.enqueue(L.dequeue());
        while (!G.isEmpty())
            s.enqueue(L.dequeue());
    }

    public static <K> void quickSort(Queue<K> S) {
        quickSort(S, Comparator.naturalOrder());
    }
}
