//3137681 Parv Patel

public class CircularDoublyLinkedList<E> {
    private Node<E> last;
    private int size;

    public CircularDoublyLinkedList() {
        this.last = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return last.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return last.getElement();
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            last = new Node<>(element, null, null);
            last.setNext(last);
        } else {
            Node<E> newNode = new Node<>(element, last, last.getNext());
            last.getNext().setPrev(newNode);
            last.setNext(newNode);
        }
        size++;
    }

    public void addLast(E element) {
        addFirst(element);
        last = last.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;

        Node<E> firstNode = last.getNext();
        if (size == 1) {
            last = null;
        } else {
            last.setNext(firstNode.getNext());
            firstNode.getNext().setPrev(last);
        }
        size--;
        return firstNode.getElement();
    }

    public E removeLast() {
        if (isEmpty()) return null;

        Node<E> removedNode = last;
        if (size == 1) {
            last = null;
        } else {
            last.getPrev().setNext(last.getNext());
            last.getNext().setPrev(last.getPrev());
            last = last.getPrev();
        }
        size--;
        return removedNode.getElement();
    }

    public void rotate() {
        if (!isEmpty()) {
            last = last.getNext();
        }
    }

    public boolean contains(E element) {
        Node<E> current = last;
        for (int i = 0; i < size; i++) {
            if (current.getElement().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    private static class Node<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (!isEmpty()) {
            Node<E> current = last.getNext();
            result.append(current.getElement());

            for (int i = 1; i < size; i++) {
                current = current.getNext();
                result.append(", ").append(current.getElement());
            }
        }
        result.append("]");
        return result.toString();
    }

    public WaitlistedStudent getElement(WaitlistedStudent studentToCheck) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getElement'");
    }

    
}
        

    
    
    

