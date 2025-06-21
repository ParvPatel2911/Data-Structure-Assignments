/*
 * From Data Structures and Algorithms in Java, Sixth Edition, Goodrich et al.
 */
// Parv Patel 3137681
/**
 * Interface for a key-value pair.
 */

public interface Entry<K, V> {

  /**
   * Returns the key stored in this entry.
   * 
   * @return the entry's key
   */
  K getKey();

  /**
   * Returns the value stored in this entry.
   * 
   * @return the entry's value
   */
  V getValue();
}
