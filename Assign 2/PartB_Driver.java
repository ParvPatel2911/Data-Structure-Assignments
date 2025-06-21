//Parv Patel 3137681
public class PartB_Driver {
    public static void main(String[] args) {

        
        ArrayPositionalList<String> list = new ArrayPositionalList<>();
        list.addLast("harry");
        list.addLast("harry");
        list.addLast("ron");
        list.addLast("ron");
        list.addLast("tom");
        list.addLast("tom");
        list.addLast("hermione");
       
        
        System.out.println("Original list: " + list);
        System.out.println("Number of original strings left after removing consecutive duplicates: " + list.removeConsecutiveDuplicates());
        System.out.println("List after removal: " + list);


    ArrayPositionalList<Character> list1 = new ArrayPositionalList<>();
    list1.addLast('S');
    list1.addLast('C');
    list1.addLast('R');
    list1.addLast('A');
    list1.addLast('M');
    list1.addLast('B');
    list1.addLast('L');
    list1.addLast('E');
    list1.addLast('G');
    list1.addLast('A');
    list1.addLast('M');
    list1.addLast('E');

    System.out.println("Original List:");
     System.out.println(list1);
     ArrayPositionalList.insertionSort(list1);

     System.out.println("Sorted characters using Insertion sort algorithm: ");
    System.out.println(list1);


    

    
    
}
    
}

