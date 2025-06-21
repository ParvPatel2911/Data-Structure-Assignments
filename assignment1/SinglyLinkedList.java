//ParvPatel 3137681
// SinglyLinkedList as provided in classnotes
public class SinglyLinkedList<E>{
    static class Node<E>{
        private E element;
        private Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
        public E getElement(){ return element;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> next){ this.next = next;}


    }
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public SinglyLinkedList(){}

    public int size(){ return 0;}
    public boolean isEmpth(){ return size == 0;}
    public E first(){
        if(isEmpth())return null;
        return head.getElement();
    }
    public E last(){
        if(isEmpth()){return null;}
        return tail.getElement();
    }

    public void addFirst(E element){
        head = new Node<>(element, head);
        if(size==0){
            tail = head;
        }
        size++;
    }

    public void addLast(E element){

        Node<E>newest = new Node<>(element, null);
        if(isEmpth()){
            head = newest;
        }
        else{
            tail.setNext(newest);
            tail= newest;
            size++;
        }
    }

    public E removeFirst(){
        if(isEmpth())return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if(size==0){
            tail=null;
            
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append("\n");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
    

}
                


    

