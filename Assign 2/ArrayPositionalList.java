

/**
 * ACS-2947 Assignment 2 Part B
 */
// Parv Patel 3137681

public class ArrayPositionalList<E> implements PositionalList<E> {

	private static class ArrPosition<E> implements Position<E> {

        private int index;
        private E element;

        public ArrPosition(int i, E e) {
            index = i;
            element = e;
        }

        public E getElement() throws IllegalStateException {
            if (index == -1) {
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }
          

        public void setElement(E e) {
            element = e;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int i) {
            index = i;
        }
        public String toString() {
            return element.toString();
        }
    }

	public static final int CAPACITY = 16;
    private ArrPosition<E>[] data;
    private int size = 0;

    public ArrayPositionalList() {
        this(CAPACITY);
    }

    
    public ArrayPositionalList(int capacity) {
        data = (ArrPosition<E>[]) new ArrPosition[capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
       
        return size==0;
    }

    public Position<E> first() {
        if (isEmpty()) {
            return null;
        }
        return data[0];
    }

    public Position<E> last() {
        if (isEmpty()) {
            return null;
        }
        return data[size - 1];
    }
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return data[index].getElement();
    }

    public Position<E> add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
            data[i + 1].setIndex(i + 1);
        }
        ArrPosition<E> newPosition = new ArrPosition<>(index, element);
        data[index] = newPosition;
        newPosition.setIndex(index);
        size++;
        return newPosition;
    }
    public Position<E> positionAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return data[index];
    }


    public Position<E> addFirst(E element) {
        if (size == data.length) {
            // Resize the array if needed
            resize(2 * data.length);
        }
        // Shift elements to make space for new element at index 0
        for (int i = size - 1; i >= 0; i--) {
            data[i + 1] = data[i];
            data[i].setIndex(i + 1);
        }
        ArrPosition<E> newPos = new ArrPosition<>(0, element);
        data[0] = newPos;
        size++;
        return newPos;
    }

    public Position<E> addLast(E element) {
        if (size == data.length) {
            // Resize the array if needed
            resize(2 * data.length);
        }
        ArrPosition<E> newPos = new ArrPosition<>(size, element);
        data[size] = newPos;
        size++;
        return newPos;
    }

    private void resize(int capacity) {
        ArrPosition<E>[] newArray = (ArrPosition<E>[]) new ArrPosition[capacity];
        System.arraycopy(data, 0, newArray, 0, size);
        data = newArray;
    }

    public int removeConsecutiveDuplicates() {
        int count = 0;
        for (int i = 0; i < size - 1; i++) {
            if (data[i].getElement().equals(data[i + 1].getElement())) {
                remove(data[i+1]);
                count++;
            }
        }
        return size - count;
    }

    public E remove(Position<E> position) {
        ArrPosition<E> pos = validate(position);
        E removedElement = pos.getElement();
        for (int i = pos.getIndex(); i < size - 1; i++) {
            data[i] = data[i + 1];
            data[i].setIndex(i);
        }
        data[size - 1] = null; 
        size--;
        return removedElement;
    }

    private ArrPosition<E> validate(Position<E> position) {
        if (!(position instanceof ArrPosition)) {
            throw new IllegalArgumentException("Invalid position");
        }
        ArrPosition<E> pos = (ArrPosition<E>) position;
        if (pos.getIndex() >= size || pos.getIndex() < 0) {
            throw new IllegalArgumentException("Invalid position");
        }
        return pos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i].getIndex()).append(":").append(data[i].getElement());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        ArrPosition<E> position = validate(p);
        int index = position.getIndex();
        if (index == 0) {
            return null; 
        }
        return data[index - 1];
        
    }

   
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        ArrPosition<E> position = validate(p);
        int index = position.getIndex();
        if (index == size - 1) {
            return null; 
        }
        return data[index + 1];
       
    }

  
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        ArrPosition<E> position = validate(p);
        int index = position.getIndex();
        add(index, e);
        return data[index];
        
    }

 
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        ArrPosition<E> position = validate(p);
        int index = position.getIndex();
        add(index + 1, e);
        return data[index + 1];
    }

   
    
        public E set(Position<E> p, E element) throws IllegalArgumentException {
            ArrPosition<E> position = validate(p);
            E temp = position.getElement();
            position.setElement(element);
            return temp;
        }

        public static void insertionSort(ArrayPositionalList<Character> list){
            int n = list.size();
            for(int k=0;k<n;k++){
                char cur = list.get(k);
                Position<Character> pivot = list.positionAtIndex(k);
        Position<Character> walk = pivot;
        while (walk != list.first() && list.before(walk).getElement() > cur) {
            walk = list.before(walk);
        }
        if (walk != pivot) {
            list.remove(pivot);
            list.addBefore(walk, cur);
        }
    }


       
      
    }
}


      








    
    



