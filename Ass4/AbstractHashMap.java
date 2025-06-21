// Parv Patel 3137681
abstract class AbstractHashMap<K, V> implements Map<K, V> {
    protected int n = 0; // number of entries
    protected int capacity; // length of the table
    private int prime; // prime factor
    private long scale, shift; // the shift and scaling factors

    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        java.util.Random rand = new java.util.Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap) {
        this(cap, 109345121);
    }

    public AbstractHashMap() {
        this(17);
    } // default capacity

    public int size() {
        return n;
    }

    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) // keep load factor <= 0.5
            resize(2 * capacity - 1); // (or find a nearby prime)
        return answer;
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    private int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    private void resize(int newCap) {
        java.util.ArrayList<Entry<K, V>> buffer = new java.util.ArrayList<>(n);
        for (Entry<K, V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable(); // based on updated capacity
        n = 0; // we are about to reinsert old entries
        for (Entry<K, V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    protected abstract void createTable();

    protected abstract V bucketGet(int h, K k);

    protected abstract V bucketPut(int h, K k, V v);

    protected abstract V bucketRemove(int h, K k);
}