// 3137681 Parv Patel
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List <E>{
    private static final int CAPACITY = 10;
    private E[] data;
    private int size;

    public ArrayList() { this(CAPACITY);}

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity2) {
       data =(E[]) new Object[capacity2];
    }

    public int size(){return size;}
    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        return data[i];
    }

    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        E temp = data[i];
        data[i]= e;
        return temp;

    }

    public void add(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i,size+1);
        if(size == data.length)throw new IllegalStateException("Array is full");
        resize(2 * data.length); 
        for(int k = size-1;k>=i;k--){
            data[k+1] = data[k];
            data[i] = e;
            size ++;
        }

    }
    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        for (int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp;
    }

    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i,size);
        E temp = data[i];
       
        for(int k =i;k<size-1;k++)
            data[k] = data[k+1];
            data[size-1] = null;
            size --;
        
        return temp;

    }

    public void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i<0 || i>=n)throw new IndexOutOfBoundsException("illegel index: " +i);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?

        // Method to check if there is a next element
        public boolean hasNext() {
            return j < size;
        }

        // Method to return the next element
        public E next() throws NoSuchElementException {
            if (j == size)
                throw new NoSuchElementException("No next element");
            removable = true;
            return data[j++];
        }

        // Method to remove the last element returned by next
        public void remove() throws IllegalStateException {
            if (!removable)
                throw new IllegalStateException("Nothing to remove");
            ArrayList.this.remove(j - 1); // that was the last one returned
            j--; // next element has shifted one cell to the left
            removable = false; // do not allow remove again until next is called
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void add(E e) {
        // Check if the current capacity is sufficient, if not, resize the array
        if (size == data.length) {
            resize(data.length * 2); // Double the capacity
        }
        data[size] = e; // Add the element to the end of the array
        size++; // Increment the size
    }
        

    

   
}
    


