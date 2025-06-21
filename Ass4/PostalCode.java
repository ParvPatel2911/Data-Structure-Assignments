
// Parv Patel 3137681
public class PostalCode implements Comparable<PostalCode> {
    private String code;
    private String area;
    private String province;
    private double latitude;
    private double longitude;

    public PostalCode(String code, String area, String province, double latitude, double longitude) {
        this.code = code;
        this.area = area;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCode() {
        return code;
    }

    public String getArea() {
        return area;
    }

    public String getProvince() {
        return province;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int compareTo(PostalCode other) {
        return this.code.compareTo(other.code);
    }

    @Override
    public String toString() {
        return "PostalCode{" +
                "code='" + code + '\'' +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}