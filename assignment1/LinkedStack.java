// 3137681 Parv Patel
// Linked Stack class with overriden methods to access stack method.
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list;

    public LinkedStack() {
        this.list = new SinglyLinkedList<>();
    }

    @Override
    public void push(E item) {
        list.addFirst(item);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E item = list.removeFirst();
        return item;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return list.first();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpth();
    }

    @Override
    public int size() {
        return list.size();
    }
}