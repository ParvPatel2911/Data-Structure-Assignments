/**
 * From Data Structures and Algorithms in Java, Sixth Edition, Goodrich et al.
 */
// Parv Patel 3137681
public interface Position<E> {
  /**
   * Returns the element stored at this position.
   *
   * @return the stored element
   * @throws IllegalStateException if position no longer valid
   */
  E getElement() throws IllegalStateException;

}
