import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
// 3137681 Parv Patel
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> implements Iterable<E> {

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        }
    }

    // Implementing Iterable interface methods
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder(); // We'll implement this method shortly
    }

    // Implementing preorder traversal
    private Iterable<Position<E>> preorder() {
        List<Position<E>> positions = new ArrayList<>();
        preorderSubtree(root(), positions);
        return positions;
    }



    // Recursive method to perform preorder traversal
    private void preorderSubtree(Position<E> p, List<Position<E>> positions) {
        if (p != null) {
            positions.add(p);
            for (Position<E> child : children(p)) {
                preorderSubtree(child, positions);
            }
        }
    }
    
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<E>(e, parent, left, right);
    }
    
    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() { }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node) // Convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    public int size() {
        return size;
    }

    public Position<E> root() {
        return root;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    
    public int height() {
        return height(root);
    }

    // Recursive method to compute the height of the tree
    private int height(Position<E> p) {
        if (isExternal(p))
            return 0;
        int maxHeight = 0;
        for (Position<E> child : children(p)) {
            maxHeight = Math.max(maxHeight, height(child));
        }
        return maxHeight + 1;
    }

    // Method to compute the depth of a given node
    public int depth(Position<E> p) {
        if (isRoot(p))
            return 0;
        return 1 + depth(parent(p));
    }

    // Method to remove a node and its subtree
    public void remove(Position<E> p) {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("Cannot remove a node with two children");
        Node<E> parent = node.getParent();
        Node<E> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        if (child != null)
            child.setParent(parent);
        if (node == root)
            root = child;
        else {
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        node.setElement(null); // Help garbage collection
        node.setParent(node); // Convention for defunct node
        node.setLeft(null);
        node.setRight(null);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 0, sb);
        return sb.toString();
    }
    
    private void toString(Position<E> p, int depth, StringBuilder sb) {
        if (p != null) {
            for (int i = 0; i < depth; i++) {
                sb.append("    "); // Adjust the indentation according to your preference
            }
            sb.append("- ").append(p.getElement()).append("\n");
            for (Position<E> child : children(p)) {
                toString(child, depth + 1, sb);
            }
        }
    }
}
