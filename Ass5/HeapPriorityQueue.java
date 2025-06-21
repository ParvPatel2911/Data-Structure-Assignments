import java.util.Comparator;
import java.util.Iterator;
// 3137681 Parv Patel
class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    public int size() {
        return heap.size();
    }

    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(heap.size(), newest);
        upheap(heap.size() - 1);
        return newest;
    }

    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }

    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    private boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    private int left(int j) {
        return 2 * j + 1;
    }

    private int right(int j) {
        return 2 * j + 2;
    }

    private int parent(int j) {
        return (j - 1) / 2;
    }

    public Iterable<Entry<K, V>> entries() {
        return new EntryIterable();
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements");
            }
            return heap.get(current++);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }
  

    
}