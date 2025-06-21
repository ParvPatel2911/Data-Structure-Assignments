
import java.util.Scanner;
// 3137681 Parv Patel

public class PartB_Driver {
    public static void main(String[] args) {
        // Create a priority queue for the standby list
        HeapPriorityQueue<Passenger, String> standbyList = new HeapPriorityQueue<>();

        // Add 10 new passengers
        System.out.println("Adding 10 new passengers to the standby list:");
        for (int i = 1; i <= 10; i++) {
            String passportNumber = getUserPassportNumber(i);
            FareCode fareCode = FareCode.randomValue();
            FlyerStatus flyerStatus = FlyerStatus.randomValue();
            long timestamp = System.currentTimeMillis(); // Get current time
            Passenger passenger = new Passenger(passportNumber, fareCode, flyerStatus, timestamp);
            standbyList.insert(passenger, passportNumber);
            System.out.println("Added Passenger: " + passenger);
            printStandbyList(standbyList);

        }

        // Remove 5 passengers based on priority
        System.out.println("\nRemoving 5 passengers based on priority:");
        for (int i = 0; i < 5; i++) {
            Entry<Passenger, String> removedPassenger = standbyList.removeMin();
            if (removedPassenger != null) {
                System.out.println("Removed Passenger: " + removedPassenger.getValue() + " Seated");
                printStandbyList(standbyList);
            } else {
                System.out.println("No more passengers to remove.");
                break;
            }

        }

        // Add 5 more passengers
        System.out.println("\nAdding 5 more passengers to the standby list:");
        for (int i = 11; i <= 15; i++) {
            String passportNumber = getUserPassportNumber(i);
            FareCode fareCode = FareCode.randomValue();
            FlyerStatus flyerStatus = FlyerStatus.randomValue();
            long timestamp = System.currentTimeMillis(); // Get current time
            Passenger passenger = new Passenger(passportNumber, fareCode, flyerStatus, timestamp);
            standbyList.insert(passenger, passportNumber);
            System.out.println("Added Passenger: " + passenger);
            printStandbyList(standbyList);
        }

        // Remove all passengers from the standby list based on priority
        System.out.println("\nRemoving all passengers from the standby list based on priority:");
        while (!standbyList.isEmpty()) {
            Entry<Passenger, String> removedPassenger = standbyList.removeMin();
            if (removedPassenger != null) {
                System.out.println("Removed Passenger: " + removedPassenger.getKey());
                printStandbyList(standbyList);
            } else {
                System.out.println("No more passengers to remove.");
                break;
            }
        }
    }

    public static void printStandbyList(HeapPriorityQueue<Passenger, String> standbyList) {
        System.out.println("\nStandby List:");

        // Check if the list is empty
        if (standbyList.entries() == null || !standbyList.entries().iterator().hasNext()) {
            System.out.println("The standby list is currently empty.");
            return;
        }

        // Print each passenger's details in priority order
        int count = 1; // To display positions in the standby list
        for (Entry<Passenger, String> entry : standbyList.entries()) {
            Passenger passenger = entry.getKey();
            System.out.println(count + ". " +
                    "Passport: " + passenger.getPassportNumber() + ", " +
                    "Fare Code: " + passenger.getFareCode() + ", " +
                    "Flyer Status: " + passenger.getFlyerStatus() + ", " +
                    "Timestamp: " + passenger.getTimestamp());
            count++;
        }
    }

    // Method to get passport number from the user
    public static String getUserPassportNumber(int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter passport number of passenger " + index + ": ");
        return scanner.nextLine();
    }

}
