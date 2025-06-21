import java.util.Comparator;
// 3137681 Parv Patel

abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {
    private Comparator<K> comp;
    @SuppressWarnings("hiding")
    class PQEntry<K, V>  implements Entry<K,V>{
        private K k;
        private V v;
    
        public PQEntry(K key, V value) {
            k =  key;
            v = value;
        }
    
        public K getKey() {
            return k;
        }
    
        public V getValue() {
            return v;
        }
        protected void setKey(K key){ k = key;}
        protected void setValue(V value) {v = value;}
        public String toString() {
            return "(" + k + ", " + v + ")";
        }
        

    
    
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try{
            return(comp.compare(key, key) == 0);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("incompatible Key");
        }
    }
   



    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}