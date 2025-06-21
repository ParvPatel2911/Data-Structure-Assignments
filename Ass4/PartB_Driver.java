import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

// Parv Patel 3137681
public class PartB_Driver {
    public static void main(String[] args) {
        LinkedPositionalChainHashMap<String, PostalCode> postalCodes = new LinkedPositionalChainHashMap<>();

        try (Scanner scanner = new Scanner(new File("PartB.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String code = data[0];
                double latitude = Double.parseDouble(data[1]);
                double longitude = Double.parseDouble(data[2]);
                PostalCode postalCode = new PostalCode(code, code, code, latitude, longitude);
                postalCodes.put(code, postalCode);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        System.out.println("Total Number of entries: " + postalCodes.size());
        System.out.println("Number of collisions: " + postalCodes.getCollisions());

        Scanner input = new Scanner(System.in);
        System.out.print("Display by code (C) or Longitude (L) (any other key to quit): ");
        String choice = input.nextLine().toUpperCase();

        if (choice.equals("C")) {
            Comparator<PostalCode> codeComparator = Comparator.comparing(PostalCode::getCode);
            displaySorted(postalCodes, codeComparator);
        } else if (choice.equals("L")) {
            Comparator<PostalCode> longitudeComparator = Comparator.comparingDouble(PostalCode::getLongitude);
            displaySorted(postalCodes, longitudeComparator);
        }

        // Inserting an entry where the same key previously exists
        String existingCode = "M3J2X8"; // Replace with a code from the sample data
        PostalCode existingPostalCode = postalCodes.get(existingCode);
        System.out.println("\nInserting an entry where the same key previously exists:");
        System.out.println("Old value: " + existingPostalCode);
        PostalCode newPostalCode = new PostalCode(existingCode, existingCode, existingCode, 43.7, -79.4);
        postalCodes.put(existingCode, newPostalCode);
        System.out.println("New value: " + postalCodes.get(existingCode));

        // Removing an entry where the key exists
        String codeToRemove = "M3J2X8"; // Replace with a code from the sample data
        System.out.println("\nRemoving an entry where the key exists:");
        System.out.println("Removed value: " + postalCodes.remove(codeToRemove));
    }

    private static <K, V> void displaySorted(LinkedPositionalChainHashMap<K, V> map, Comparator<V> comparator) {
        Queue<V> values = new LinkedQueue<>();
        for (V value : map.values()) {
            values.enqueue(value);
        }

        @SuppressWarnings("unchecked")
        V[] valuesArray = (V[]) new Object[values.size()];
        for (int i = 0; i < valuesArray.length; i++) {
            valuesArray[i] = values.dequeue();
        }

        MergeSort.sort(valuesArray, comparator);

        for (V value : valuesArray) {
            System.out.println(value);
        }
    }
}
