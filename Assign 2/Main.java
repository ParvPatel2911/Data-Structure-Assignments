// Parv Patel 3137681
public class Main {
    
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
       
        for (int i = 0; i < 10; i++) {
            list.add(i,i);
            System.out.println("Size: " + list.size() + ", Capacity: " + list.getCapacity());
        }
        
        
        for (int i = 0; i < 5; i++) {
            list.remove(i);
            System.out.println("Size: " + list.size() + ", Capacity: " + list.getCapacity());
        }
    }
}
