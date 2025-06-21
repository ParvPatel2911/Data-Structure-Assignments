// 3137681 Parv Patel
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null; // p is root, has no sibling
        if (p == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }
    
     public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> children = new ArrayList<>(2); // At most two children in a binary tree
        if (left(p) != null) children.add(left(p));
        if (right(p) != null) children.add(right(p));
        return children;
    }
}
