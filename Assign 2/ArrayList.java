
//Parv Patel 3137681
import java.util.Iterator;
public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 4;
    private E[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }



    
    public ArrayList(int Capacity) {
    elements = (E[]) new Object[Capacity];
    
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){return size == 0;}

    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i,size);
        return elements[i];
    }
        public E set(int i, E e) throws IndexOutOfBoundsException {
            checkIndex(i,size);
            E temp = elements[i];
            elements[i] = e;
           return temp;
        }




    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
       checkIndex(i,size+1);
       if(size == elements.length)
       throw new IllegalStateException("Array is full");
       for(int k = size-1; k >= i ; k++ ){
        elements[k+1] = elements[k];
        elements[i] = e;
        size++;
       }
    }

   

    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        E temp = elements[i];
        for(int k = i; k<size-1;k++){
            elements[k] = elements[k+1];
            elements[size-1] = null;
            size --;
        }
        return temp;
    }

    public void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i<0 || i>=n)throw new IndexOutOfBoundsException("illegal index: " +i);
    }

    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                return elements[currentIndex++];
            }
        };
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArrayList<?> arrayList = (ArrayList<?>) obj;
        if (size != arrayList.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(arrayList.elements[i])) {
                return false;
            }
        }
        return true;
    }
    

   






    
}
