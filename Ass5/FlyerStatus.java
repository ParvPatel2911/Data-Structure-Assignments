import java.util.Random;
// 3137681 Parv Patel
enum FlyerStatus {
    GOLD, SILVER, BRONZE, NONE;

    // Method to randomly choose a flyer status
    public static FlyerStatus randomValue() {
        FlyerStatus[] values = FlyerStatus.values();
        int index = new Random().nextInt(values.length);
        return values[index];
    }
}
