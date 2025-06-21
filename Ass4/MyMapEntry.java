
// Parv Patel 3137681
class MyMapEntry<K, V> implements Entry<K, V> {
    private K k;
    private V v;

    public MyMapEntry(K key, V value) {
        k = key;
        v = value;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    protected void setKey(K key) {
        k = key;
    }

    public V setValue(V value) {
        V old = v;
        v = value;
        return old;
    }

    @Override
    public Entry<K, V>[] entrySet() {
        return null;

    }
}
