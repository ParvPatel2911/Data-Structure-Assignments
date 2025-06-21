// 3137681 Parv Patel
class Passenger implements Comparable<Passenger> {
    private String passportNumber;
    private FareCode fareCode;
    private FlyerStatus flyerStatus;
    private long timestamp;

    public Passenger(String passportNumber, FareCode fareCode, FlyerStatus flyerStatus, long timestamp) {
        this.passportNumber = passportNumber;
        this.fareCode = fareCode;
        this.flyerStatus = flyerStatus;
        this.timestamp = timestamp;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public FareCode getFareCode() {
        return fareCode;
    }

    public FlyerStatus getFlyerStatus() {
        return flyerStatus;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Implementing Comparable interface
    @Override
    public int compareTo(Passenger other) {
        // First comparing by fare code
        int fareCodeComparison = this.fareCode.compareTo(other.fareCode);
        if (fareCodeComparison != 0)
            return fareCodeComparison;

        // If fare codes are the same, compare by flyer status
        int flyerStatusComparison = this.flyerStatus.compareTo(other.flyerStatus);
        if (flyerStatusComparison != 0)
            return flyerStatusComparison;

        // If flyer statuses are the same, compare by timestamp
        return Long.compare(this.timestamp, other.timestamp);
    }

    public String toString() {
        return "Passenger{" +
                "passportNumber='" + passportNumber + '\'' +
                ", fareCode=" + fareCode +
                ", flyerStatus=" + flyerStatus +
                ", timestamp=" + timestamp +
                '}';
    }
}