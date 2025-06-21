import java.util.Random;
// 3137681 Parv Patel
enum FareCode {
    FULL, DISC, BUDDY;

    // Method to randomly choose a fare code
    public static FareCode randomValue() {
        FareCode[] values = FareCode.values();
        int index = new Random().nextInt(values.length);
        return values[index];
    }
}
