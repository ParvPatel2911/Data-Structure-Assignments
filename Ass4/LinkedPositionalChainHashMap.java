import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Parv Patel 3137681
class LinkedPositionalChainHashMap<K, V> extends AbstractHashMap<K, V> {
    private PositionalList<Entry<K, V>>[] table;
    private int collisions; // variable to track collisions
    public MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public LinkedPositionalChainHashMap() {
        super();
    }

    public LinkedPositionalChainHashMap(int cap) {
        super(cap);
    }

    public LinkedPositionalChainHashMap(int cap, int p) {
        super(cap, p);
    }

    protected void createTable() {
        table = (PositionalList<Entry<K, V>>[]) new PositionalList[capacity];
        for (int i = 0; i < capacity; i++)
            table[i] = new LinkedPositionalList<>();
    }

    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    protected V bucketGet(int h, K k) {
        LinkedPositionalList<Entry<K, V>> bucket = table[h];
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> e = bucket.get(i);
            if (e.getKey().equals(k))
                return e.getValue();

        }

        return null;
    }

    protected V bucketPut(int h, K k, V v) {
        PositionalList<Entry<K, V>> bucket = table[h];
        for (Entry<K, V> e : bucket)
            if (e.getKey().equals(k))
                return ((MyMapEntry<K, V>) e).setValue(v);
        bucket.addLast(new MyMapEntry<>(k, v));
        n++;
        return null;
    }

    protected V bucketRemove(int h, K k) {
        PositionalList<Entry<K, V>> bucket = table[h];
        for (Entry<K, V> e : bucket) {
            if (e.getKey().equals(k)) {
                V value = e.getValue();
                bucket.remove(bucket.position((Position<Entry<K, V>>) e));
                n--;
                return value;
            }
        }
        return null;
    }

    public int getCollisions() {
        return collisions;
    }

    protected void resize(int newCap) {
        PositionalList<Entry<K, V>>[] oldTable = table;
        table = (PositionalList<Entry<K, V>>[]) new PositionalList[newCap];
        for (int i = 0; i < newCap; i++)
            table[i] = new LinkedPositionalList<>();
        for (PositionalList<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                int h = hashValue(entry.getKey());
                table[h].addLast(entry);
            }
        }
        capacity = newCap;
    }

    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        private class KeyIterator implements Iterator<K> {
            private Iterator<Entry<K, V>> entryIterator = (Iterator<Entry<K, V>>) iterator();

            @Override
            public boolean hasNext() {
                return entryIterator.hasNext();
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return entryIterator.next().getKey();
            }
        }
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        private class ValueIterator implements Iterator<V> {
            private Iterator<Entry<K, V>> entryIterator = (Iterator<Entry<K, V>>) iterator();

            @Override
            public boolean hasNext() {
                return entryIterator.hasNext();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return entryIterator.next().getValue();
            }
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null) {
                for (Entry<K, V> entry : table[h]) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}